package com.auctart.web;

import com.auctart.service.BidService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/bid")
public class BidController {

    private final BidService service;

    public BidController(BidService service){
        this.service = service;
    }
}
