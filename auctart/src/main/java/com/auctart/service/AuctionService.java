package com.auctart.service;

import com.auctart.repository.AuctionRepository;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {
    private final AuctionRepository repository;

    AuctionService(AuctionRepository repository){
        this.repository = repository;
    }
}
