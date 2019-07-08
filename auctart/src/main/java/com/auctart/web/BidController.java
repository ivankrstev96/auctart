package com.auctart.web;

import com.auctart.domain.Auction;
import com.auctart.domain.Bid;
import com.auctart.domain.User;
import com.auctart.service.BidService;
import com.auctart.web.dto.BidDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/api/bid")
public class BidController {

    private final BidService service;

    public BidController(BidService service) {
        this.service = service;
    }

    @PostMapping
    public void saveBid(@RequestBody BidDto bidDto, Authentication authentication){
        this.service.saveBid(bidDto, ((HashMap<String, User>) authentication.getDetails()).get("account"));
    }
}
