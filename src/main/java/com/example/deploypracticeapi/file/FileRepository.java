package com.example.deploypracticeapi.file;
import com.example.deploypracticeapi.jpa.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Long> {
    Optional<FileEntity> findByFileIDAndDelYn(Long fileID,  String delYn);

}
