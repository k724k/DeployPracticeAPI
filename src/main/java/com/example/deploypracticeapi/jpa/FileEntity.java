package com.example.deploypracticeapi.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FILE")
@Builder
@Getter
@Setter
@DynamicUpdate

public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long fileID;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "url")
    private String url;

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

}


