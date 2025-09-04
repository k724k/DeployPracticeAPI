package api.jpa;

import api.board.BoardModelDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOARD")
@Builder
@Getter
@Setter
@DynamicUpdate
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardID;

    @Column(name = "board_name",length = 100,unique = true)
    private String boardName;

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

    public void updateBoard(BoardModelDto.UpdateBoardModel updateBoardModel){
        this.boardName = updateBoardModel.getBoardName() != null
                ? updateBoardModel.getBoardName().trim()
                : this.boardName;

        this.title = updateBoardModel.getTitle() != null
                ? updateBoardModel.getTitle().trim()
                : this.title;

        this.content = updateBoardModel.getContent() != null
                ? updateBoardModel.getContent().trim()
                : this.content;

        this.subDescr1 = updateBoardModel.getSubDescr1() != null
                ? updateBoardModel.getSubDescr1().trim()
                : this.subDescr1;

        this.subDescr2 = updateBoardModel.getSubDescr2() != null
                ? updateBoardModel.getSubDescr2().trim()
                : this.subDescr2;

        this.subDescr3 = updateBoardModel.getSubDescr3() != null
                ? updateBoardModel.getSubDescr3().trim()
                : this.subDescr3;

        this.useYn = updateBoardModel.getUseYn() != null
                ? updateBoardModel.getUseYn().trim()
                : this.useYn;
    }

}
