package com.auctart.web;

import com.auctart.service.ImageService;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/image")
public class ImageController {
    private final ImageService service;

    ImageController(ImageService service){
        this.service = service;
    }
}
