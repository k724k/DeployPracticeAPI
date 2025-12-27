package com.example.deploypracticeapi.jpa;

import com.example.deploypracticeapi.board.BoardModelDto;
import com.example.deploypracticeapi.memo.MemoModelDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMO")
@Builder
@Getter
@Setter
@DynamicUpdate
public class MemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_id")
    private Long memoID;

    @Column(name = "title", length = 255)
    private String title;

    @Column
    private String content;

    @Column(name = "sub_descr1", length = 100)
    private String subDescr1;

    @Column(name = "sub_descr2",length = 100)
    private String subDescr2;

    @Column(name = "sub_descr3",length = 100)
    private String subDescr3;

    @Column(name = "use_yn")
    @Builder.Default
    private String useYn = "Y";

    @Column(name = "del_yn")
    @Builder.Default
    private String delYn = "N";

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String regDate;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updDate;

    @PrePersist
    public void onPrePersist() {
        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setDelYn(){
        this.delYn = "Y";
        this.onPreUpdate();
    }

    public void updateMemo(MemoModelDto.UpdateMemoModel updateMemoModel){

        this.title = updateMemoModel.getTitle() != null
                ? updateMemoModel.getTitle().trim()
                : this.title;

        this.content = updateMemoModel.getContent() != null
                ? updateMemoModel.getContent().trim()
                : this.content;

        this.subDescr1 = updateMemoModel.getSubDescr1() != null
                ? updateMemoModel.getSubDescr1().trim()
                : this.subDescr1;

        this.subDescr2 = updateMemoModel.getSubDescr2() != null
                ? updateMemoModel.getSubDescr2().trim()
                : this.subDescr2;

        this.subDescr3 = updateMemoModel.getSubDescr3() != null
                ? updateMemoModel.getSubDescr3().trim()
                : this.subDescr3;

        this.useYn = updateMemoModel.getUseYn() != null
                ? updateMemoModel.getUseYn().trim()
                : this.useYn;
    }





}
