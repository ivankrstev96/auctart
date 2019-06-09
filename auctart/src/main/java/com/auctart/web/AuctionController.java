package com.auctart.web;

import com.auctart.domain.Auction;
import com.auctart.service.AuctionService;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/auction")
public class AuctionController {

    private final AuctionService service;

    AuctionController(AuctionService service){
        this.service = service;
    }
}
