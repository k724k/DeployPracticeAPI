package com.example.deploypracticeapi.file;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // 1️⃣ MultipartFile → 임시 파일로 저장
        Path tempFile = Files.createTempFile("upload-", fileName);
        file.transferTo(tempFile);

        try {
            // 2️⃣ S3에 업로드
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .contentType(file.getContentType())
                            .build(),
                    tempFile
            );

            // 3️⃣ URL 반환
            return "https://" + bucketName + ".s3." + s3Client.serviceClientConfiguration().region().id() + ".amazonaws.com/" + fileName;
        } catch (S3Exception e) {
            throw new RuntimeException("S3 업로드 중 오류 발생: " + e.awsErrorDetails().errorMessage(), e);
        } finally {
            Files.deleteIfExists(tempFile); // 4️⃣ 임시 파일 삭제
        }
    }
}
