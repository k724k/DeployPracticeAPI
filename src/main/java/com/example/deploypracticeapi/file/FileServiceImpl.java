package com.example.deploypracticeapi.file;

import com.example.deploypracticeapi.jpa.FileEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    S3Service s3Service;

    @Override
    public List<FileModelDto.FileModel> getFileList() {

        List<FileEntity> fileEntities = fileRepository.findAll();
        List<FileModelDto.FileModel> fileListModelList = new ArrayList<>();
        for (FileEntity file : fileEntities) {

            FileModelDto.FileModel fileModel = new FileModelDto.FileModel();
            fileModel.setFileID(file.getFileID());
            fileModel.setTitle(file.getTitle());
            fileModel.setContent(file.getContent());
            fileModel.setUrl(file.getUrl());
            fileModel.setDelYn(file.getDelYn());
            fileModel.setRegDate(file.getRegDate());
            fileModel.setUpdDate(file.getUpdDate());
            fileListModelList.add(fileModel);
        }
        return fileListModelList;

    }

    @Override
    public String insertFile(FileModelDto.UpdateFileModel updateFileModel) {
        String resMsg = "";
        try {
            // 1️⃣ S3 업로드
            String fileUrl = s3Service.uploadFile(updateFileModel.getFile());

            // 2️⃣ DB 저장
            FileEntity entity = fileRepository.save(
                    FileEntity.builder()
                            .title(updateFileModel.getTitle())
                            .content(updateFileModel.getContent())
                            .url(fileUrl)
                            .build()
            );


            log.info("Saved entity: " + entity.getFileID() + ", url: " + entity.getUrl());
            resMsg = "SUCCESS";

        } catch (Exception e) {
            log.info("insertFile Exception ::::: " + e);
            resMsg = e.getMessage();
        }

        return resMsg;
    }

    @Override
    public FileModelDto.FileModel getFileDetail(Long fileID) {
        FileEntity entity = fileRepository.findByFileIDAndDelYn(fileID, "N")
                .orElseThrow(() -> new IllegalArgumentException("fileID 확인 필요 fileID : " + fileID));

        return modelMapper.map(entity, FileModelDto.FileModel.class);
    }

    @Override
    public String deleteFile(Long fileID) {

        String resMsg = "";
        try {
            FileEntity entity = fileRepository.findByFileIDAndDelYn(fileID, "N")
                    .orElseThrow(() -> new IllegalArgumentException("fileID 확인 필요 fileID : " + fileID));

            entity.setDelYn();
            fileRepository.save(entity);
            resMsg = "SUCCESS";

        } catch (Exception e) {
            log.info("deleteFile Exception ::::: " + e);
            resMsg = e.getMessage();
        }
        return resMsg;
    }

}
