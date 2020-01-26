package com.auctart.service;

import com.auctart.domain.Auction;
import com.auctart.domain.Bid;
import com.auctart.domain.Image;
import com.auctart.domain.User;
import com.auctart.repository.AuctionRepository;
import com.auctart.repository.BidRepository;
import com.auctart.repository.ImageRepository;
import com.auctart.web.dto.AuctionDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    private final AuctionRepository repository;
    private final BidRepository bidRepository;
    private final ImageService imageService;

    public AuctionService(AuctionRepository repository,
                          BidRepository bidRepository,
                          ImageService imageService) {
        this.repository = repository;
        this.bidRepository = bidRepository;
        this.imageService = imageService;
    }

    public List<Auction> getActiveAuctions() {
        return this.repository.findAll().stream().filter(auction -> auction
                .getEndDate()
                .isAfter(LocalDateTime.now()))
                .sorted((y,x)-> {
                    if(x.getEndDate().isBefore(y.getEndDate()))
                        return 1;
                    if(x.getEndDate().isEqual(y.getEndDate()))
                        return 0;
                    return -1;
                })
                .collect(Collectors.toList());
    }

    public List<Auction> searchActiveAuctions(String query) {
        return this.repository.findAll()
                .stream().filter(auction -> auction
                        .getEndDate()
                        .isAfter(LocalDateTime.now())
                        && (auction.getName().toLowerCase().contains(query.toLowerCase())
                        || auction.getAuthor().toLowerCase().contains(query.toLowerCase())
                ))
                .sorted((y,x)-> {
                    if(x.getEndDate().isBefore(y.getEndDate()))
                        return 1;
                    if(x.getEndDate().isEqual(y.getEndDate()))
                        return 0;
                    return -1;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Auction saveAuction(AuctionDto dto, User user) throws IOException {
        Image image = this.imageService.save(dto.image);
        Auction auction = new Auction(dto.name, dto.author, LocalDateTime.now(),
                dto.endDate, dto.startPrice, user, image);
        return this.repository.save(auction);
    }

    public Optional<Bid> getHighestBidForAuction(Long auctionId) {
        return this.bidRepository.findAll().stream()
                .filter(bid -> bid.getAuction().getId().equals(auctionId))
                .max(Comparator.comparingInt(Bid::getPrice));
    }
}
