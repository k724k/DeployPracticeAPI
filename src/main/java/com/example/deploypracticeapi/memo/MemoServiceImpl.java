package com.example.deploypracticeapi.memo;

import com.example.deploypracticeapi.board.BoardModelDto;
import com.example.deploypracticeapi.jpa.BoardEntity;
import com.example.deploypracticeapi.jpa.MemoEntity;
import com.example.deploypracticeapi.model.Pagenation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MemoServiceImpl implements MemoService {

    @Autowired
    MemoRepository memoRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MemoModelDto.MemoModel> getMemoList(Pagenation pagenation) {
        Pageable pageable = pagenation.generatePageable();

        Page<MemoEntity> memoEntityPage = memoRepository.findAll(pageable);  // findAll 사용
        List<MemoEntity> memoEntities = memoEntityPage.getContent();

        List<MemoModelDto.MemoModel> memoListModelList = new ArrayList<>();
        for (MemoEntity memo : memoEntities) {  // memo 사용
            MemoModelDto.MemoModel memoListModel = new MemoModelDto.MemoModel();  // MemoModel 사용
            memoListModel.setMemoID(memo.getMemoID());      // memo.getMemoID()
            memoListModel.setTitle(memo.getTitle());
            memoListModel.setContent(memo.getContent());
            memoListModel.setSubDescr1(memo.getSubDescr1());
            memoListModel.setSubDescr2(memo.getSubDescr2());
            memoListModel.setSubDescr3(memo.getSubDescr3());
            memoListModel.setUseYn(memo.getUseYn());
            memoListModel.setDelYn(memo.getDelYn());
            memoListModel.setRegDate(memo.getRegDate());
            memoListModel.setUpdDate(memo.getUpdDate());
            memoListModelList.add(memoListModel);
        }
        return memoListModelList;
    }


    @Override
    public MemoModelDto.MemoModel getMemoDetail(Long memoID) {

        MemoEntity entity = memoRepository.findByMemoIDAndDelYn(memoID, "N")
                .orElseThrow(() -> new IllegalArgumentException("memoID 확인 필요 memoID : " + memoID));

        return modelMapper.map(entity, MemoModelDto.MemoModel.class);

    }


    @Override
    public String insertMemo(MemoModelDto.UpdateMemoModel updateMemoModel) {
        String resMsg = "";
        try {

            var checkTitleName = memoRepository.findByTitleAndDelYn(updateMemoModel.getTitle(), "N");

            if (checkTitleName.isPresent()) {
                throw new IllegalArgumentException("이미 존재하는 제목 입니다: " + updateMemoModel.getTitle());
            }

            MemoEntity entity = memoRepository.save(
                    MemoEntity.builder()
                            .title(updateMemoModel.getTitle() != null ? updateMemoModel.getTitle().trim() : null)
                            .content(updateMemoModel.getContent() != null ? updateMemoModel.getContent().trim() : null)
                            .subDescr1(updateMemoModel.getSubDescr1() != null ? updateMemoModel.getSubDescr1().trim() : null)
                            .subDescr2(updateMemoModel.getSubDescr2() != null ? updateMemoModel.getSubDescr2().trim() : null)
                            .subDescr3(updateMemoModel.getSubDescr3() != null ? updateMemoModel.getSubDescr3().trim() : null)
                            .useYn(updateMemoModel.getUseYn() != null ? updateMemoModel.getUseYn().trim() : null)
                            .build());

            resMsg = "SUCCESS";


        } catch (Exception e) {
            log.info("insertMemo Exception ::::: " + e);
            resMsg = e.getMessage();
        }

        return resMsg;

    }

    @Override
    public String updateMemo(Long memoID, MemoModelDto.UpdateMemoModel updateMemoModel) {

        String resMsg = "";
        try {
            MemoEntity entity = memoRepository.findByMemoIDAndDelYn(memoID, "N")
                    .orElseThrow(() -> new IllegalArgumentException("memoID 확인 필요 memoID : " + memoID));
            if (entity.getTitle().equals(updateMemoModel.getTitle())) {
                throw new IllegalArgumentException("이미 존재하는 제목 입니다: " + updateMemoModel.getTitle());
            }

            entity.updateMemo(updateMemoModel);
            memoRepository.save(entity);
            resMsg = "SUCCESS";

        } catch (Exception e) {
            log.info("updateMemo Exception ::::: " + e);
            resMsg = e.getMessage();
        }
        return resMsg;
    }

    @Override
    @Transactional
    public String deleteMemo(Long memoID) {
        String resMsg = "";
        try {
            MemoEntity entity = memoRepository.findByMemoIDAndDelYn(memoID, "N")
                    .orElseThrow(() -> new IllegalArgumentException("memoID 확인 필요: " + memoID));

            entity.setDelYn();
            memoRepository.save(entity);

            resMsg = "SUCCESS";
        } catch (Exception e) {
            log.error("deleteMemo 실패 memoID={}, 오류={}", memoID, e.getMessage(), e);
            resMsg = "DELETE_FAILED: " + e.getMessage();
        }
        return resMsg;
    }


}
