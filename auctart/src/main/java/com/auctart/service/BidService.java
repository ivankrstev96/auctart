package com.auctart.service;

import com.auctart.domain.Bid;
import com.auctart.domain.User;
import com.auctart.repository.AuctionRepository;
import com.auctart.repository.BidRepository;
import com.auctart.web.dto.BidDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BidService {

    private final BidRepository repository;
    private final AuctionRepository auctionRepository;

    public BidService(BidRepository repository, AuctionRepository auctionRepository){
        this.repository = repository;
        this.auctionRepository = auctionRepository;
    }

    @Transactional
    public void saveBid(BidDto dto, User user){
        Bid bid = new Bid(user, auctionRepository.getOne(dto.auction), dto.price);
        this.repository.save(bid);
    }
}
