package com.auctart.service;

import com.auctart.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository repository;

    ImageService(ImageRepository repository){
        this.repository = repository;
    }
}
