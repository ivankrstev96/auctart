package com.auctart.service;

import com.auctart.domain.Image;
import com.auctart.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository repository;
    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

    ImageService(ImageRepository repository){
        this.repository = repository;
    }

    public void save(MultipartFile file) throws IOException {
        byte[] primitiveBytes = file.getBytes();
        Byte[] bytes = new Byte[primitiveBytes.length];
        for (int i=0; i<primitiveBytes.length; i++) {
            bytes[i] = primitiveBytes[i];
        }
        Image image = new Image(file.getName(), bytes, file.getContentType(), file.getSize());
        logger.info("Image saved");
        repository.save(image);
    }
}
