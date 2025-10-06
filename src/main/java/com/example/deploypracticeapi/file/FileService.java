package com.example.deploypracticeapi.file;

import java.util.List;

public interface FileService {

    List<FileModelDto.FileModel> getFileList();

    String insertFile(FileModelDto.UpdateFileModel updateFileModel);

    FileModelDto.FileModel getFileDetail(Long fileID);

    String deleteFile(Long fileID);
}
