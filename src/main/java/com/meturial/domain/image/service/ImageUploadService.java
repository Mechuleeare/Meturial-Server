package com.meturial.domain.image.service;

import com.meturial.domain.image.presentation.dto.response.ImageUrlResponse;
import com.meturial.infra.s3.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageUploadService {

    private final S3Facade s3Facade;

    public ImageUrlResponse uploadImage(List<MultipartFile> images) {
        List<String> imageUrl = images.stream()
                .map(s3Facade::uploadImage)
                .toList();

        return new ImageUrlResponse(imageUrl);
    }
}
