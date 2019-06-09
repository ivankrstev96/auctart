package com.auctart.service;

import com.auctart.repository.BidRepository;
import org.springframework.stereotype.Service;

@Service
public class BidService {

    private final BidRepository repository;

    BidService(BidRepository repository){
        this.repository = repository;
    }
}
