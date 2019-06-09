package com.auctart.web;

import com.auctart.service.BidService;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/bid")
public class BidController {
    private final BidService service;

    BidController(BidService service){
        this.service = service;
    }
}
