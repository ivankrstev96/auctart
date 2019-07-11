package com.auctart.web;

import com.auctart.domain.Auction;
import com.auctart.domain.Bid;
import com.auctart.domain.User;
import com.auctart.service.AuctionService;
import com.auctart.web.dto.AuctionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/auction")
public class AuctionController {

    private final AuctionService service;

    public AuctionController(AuctionService service) {
        this.service = service;
    }

    @GetMapping("/public")
    public List<Auction> getActiveAuctions() {
        return this.service.getActiveAuctions();
    }

    @GetMapping(value = "/public", params = "query")
    public List<Auction> searchActiveAuction(@PathParam("query") String query) {
        return this.service.searchActiveAuctions(query);
    }

    @PostMapping
    public void saveAuction(@Valid AuctionDto auctionDto, Authentication authentication) throws IOException {
        this.service.saveAuction(auctionDto, ((HashMap<String, User>) authentication.getDetails()).get("account"));
    }

    @GetMapping("/public/{auctionId}/bid")
    public ResponseEntity<Bid> getHighestBidForAuction(@PathVariable(name = "auctionId") Long auctionId) {
        return this.service.getHighestBidForAuction(auctionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

}
