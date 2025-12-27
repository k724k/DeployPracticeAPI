package com.example.deploypracticeapi.memo;

import com.example.deploypracticeapi.model.Pagenation;

import java.util.List;

public interface MemoService {

    List<MemoModelDto.MemoModel> getMemoList(Pagenation pagenation);

    MemoModelDto.MemoModel getMemoDetail(Long memoId);

    String insertMemo(MemoModelDto.UpdateMemoModel updateMemoModel);

    String updateMemo(Long memoId,MemoModelDto.UpdateMemoModel updateMemoModel);

    String deleteMemo(Long memoId);

}
