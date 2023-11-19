package com.meturial.infra.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.meturial.global.exception.SaveImageFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Facade {

    private static final String FOLDER_NAME = "image";
    private final AmazonS3Client amazonS3Client;
    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadImage(MultipartFile image) {
        String fileName = FOLDER_NAME + "/" + UUID.randomUUID() + image.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    fileName,
                    image.getInputStream(),
                    getObjectMetadata(image)
            );
            amazonS3Client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            e.printStackTrace();
            throw SaveImageFailedException.EXCEPTION;
        }

        return amazonS3Client.getUrl(bucketName, fileName).toString();
    }

    private ObjectMetadata getObjectMetadata(MultipartFile image) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType(image.getContentType());

        return objectMetadata;
    }
}
