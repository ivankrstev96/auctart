package com.auctart.service;

import com.auctart.domain.Auction;
import com.auctart.domain.Bid;
import com.auctart.domain.User;
import com.auctart.repository.AuctionRepository;
import com.auctart.repository.BidRepository;
import com.auctart.web.dto.AuctionDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    private final AuctionRepository repository;
    private final BidRepository bidRepository;

    public AuctionService(AuctionRepository repository, BidRepository bidRepository) {
        this.repository = repository;
        this.bidRepository = bidRepository;
    }

    public List<Auction> getActiveAuctions() {
        return this.repository.findAll().stream().filter(auction -> auction
                .getEndDate()
                .isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveAuction(AuctionDto dto, User user) {
        Auction auction = new Auction(dto.name, dto.author, LocalDateTime.now(),
                dto.endDate, dto.startPrice, user);
        this.repository.save(auction);
    }

    public Optional<Bid> getHighestBidForAuction(Long auctionId) {
        return this.bidRepository.findAll().stream()
                .filter(bid -> bid.getAuction().getId().equals(auctionId))
                .max(Comparator.comparingInt(Bid::getPrice));
    }
}
