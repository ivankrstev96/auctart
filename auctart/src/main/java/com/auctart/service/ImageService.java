package com.auctart.service;

import com.auctart.domain.Image;
import com.auctart.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Observable;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository repository;
    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

    public ImageService(ImageRepository repository){
        this.repository = repository;
    }

    public Image save(MultipartFile file) throws IOException {
        byte[] primitiveBytes = file.getBytes();
        Byte[] bytes = new Byte[primitiveBytes.length];
        for (int i=0; i<primitiveBytes.length; i++) {
            bytes[i] = primitiveBytes[i];
        }
        Image image = new Image(file.getName(), bytes, file.getContentType(), file.getSize());
        logger.info("Image saved");
        return repository.save(image);
    }

    public Optional<Byte[]> findImage(Long id) {
        return this.repository.findById(id)
                .map(Image::getBytes);
    }
}
