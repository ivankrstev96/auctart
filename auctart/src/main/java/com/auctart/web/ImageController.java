package com.auctart.web;

import com.auctart.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@RequestParam("file") MultipartFile file) {

        try {
            this.service.save(file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/public/{id}")
    public ResponseEntity<Byte[]> findImage(@PathVariable(name = "id") Long id, HttpServletResponse response) {
        return this.service.findImage(id)
                .map(x -> {
                    try {
                        OutputStream os = response.getOutputStream();
                        for (byte b : x) {
                            os.write(b);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return ResponseEntity.ok(x);
                })
                .orElse(ResponseEntity.badRequest().build());
    }
}
