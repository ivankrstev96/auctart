package com.auctart.web;

import com.auctart.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.rmi.server.ExportException;

@Controller
@RequestMapping("api/image")
public class ImageController {
    private final ImageService service;

    ImageController(ImageService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@RequestParam("file") MultipartFile file){

        try {
            this.service.save(file);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
