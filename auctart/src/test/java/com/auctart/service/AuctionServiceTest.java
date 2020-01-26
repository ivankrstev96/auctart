package com.auctart.service;

import com.auctart.domain.Auction;
import com.auctart.domain.Bid;
import com.auctart.domain.Image;
import com.auctart.domain.User;
import com.auctart.repository.AuctionRepository;
import com.auctart.repository.BidRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
    --- searchActiveAuctions(String query) ---
    --- CACC ---

    CLAUSES:
        a: auction end date is after today
        b: auction name contains query
        c: author name contains query

    PREDICATE:
        a && (b || c)

    TEST-CASES:
        p(a):
            1: a=T, b=t, c=f
            2: a=F, b=t, c=f
        p(b):
            3: a=t, b=T, c=f (duplicate)
            4: a=t, b=F, c=f
        p(c):
            5: a=t, b=f, c=T
            6: a=t, b=f, c=F (duplicate)
*/

@RunWith(SpringRunner.class)
public class AuctionServiceTest {

    @MockBean
    public AuctionRepository repository;
    @MockBean
    public BidRepository bidRepository;
    @MockBean
    public ImageService imageService;
    @MockBean
    public AuctionService auctionService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        auctionService = new AuctionService(repository, bidRepository, imageService);
    }

    @Test
    public void searchActiveAuctionsTest1() {
        // given
        final String query = "qwe";
        List<Auction> auctionsMock = new ArrayList<>();

        LocalDateTime before = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime after = LocalDateTime.of(2100, 1, 1, 0, 0);
        User user = new User();
        Image image = new Image();
        Auction auction = new Auction("qwer", "asdf", before, after, 100, user, image);

        auctionsMock.add(auction);
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        //when
        List<Auction> auctions = auctionService.searchActiveAuctions(query);

        //then
        assertTrue(auctions.contains(auction));
    }

    @Test
    public void searchActiveAuctionsTest2() {
        // given
        final String query = "qwe";
        List<Auction> auctionsMock = new ArrayList<>();

        LocalDateTime before = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime after = LocalDateTime.of(2020, 1, 1, 0, 0);
        User user = new User();
        Image image = new Image();
        Auction auction = new Auction("qwer", "asdf", before, after, 100, user, image);

        auctionsMock.add(auction);
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        //when
        List<Auction> auctions = auctionService.searchActiveAuctions(query);

        //then
        assertFalse(auctions.contains(auction));
    }

    @Test
    public void searchActiveAuctionsTest4() {
        // given
        final String query = "qwe";
        List<Auction> auctionsMock = new ArrayList<>();

        LocalDateTime before = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime after = LocalDateTime.of(2100, 1, 1, 0, 0);
        User user = new User();
        Image image = new Image();
        Auction auction = new Auction("asdf", "asdf", before, after, 100, user, image);

        auctionsMock.add(auction);
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        //when
        List<Auction> auctions = auctionService.searchActiveAuctions(query);

        //then
        assertFalse(auctions.contains(auction));
    }

    @Test
    public void searchActiveAuctionsTest5() {
        // given
        final String query = "qwe";
        List<Auction> auctionsMock = new ArrayList<>();

        LocalDateTime before = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime after = LocalDateTime.of(2100, 1, 1, 0, 0);
        User user = new User();
        Image image = new Image();
        Auction auction = new Auction("asdf", "qwer", before, after, 100, user, image);

        auctionsMock.add(auction);
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        //when
        List<Auction> auctions = auctionService.searchActiveAuctions(query);

        //then
        assertTrue(auctions.contains(auction));
    }

    @Test
    public void searchActiveAuctionsEmptyListTest() {
        // given
        final String query = "qwe";
        List<Auction> auctionsMock = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        //when
        List<Auction> auctions = auctionService.searchActiveAuctions(query);

        //then
        assertEquals(0, auctions.size());
    }

    @Test
    public void searchActiveAuctionsSortedListTest() {
        // given
        final String query = "qwe";
        List<Auction> auctionsMock = new ArrayList<>();

        LocalDateTime before = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime after = LocalDateTime.of(2100, 1, 1, 0, 0);
        LocalDateTime after2 = LocalDateTime.of(2080, 1, 1, 0, 0);
        User user = new User();
        Image image = new Image();
        Auction auction = new Auction("asdf", "qwer", before, after, 100, user, image);
        Auction auction2 = new Auction("asdf", "qwer", before, after2, 100, user, image);

        auctionsMock.add(auction);
        auctionsMock.add(auction2);
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        List<Auction> sortedList = Arrays.asList(auction2, auction);

        //when
        List<Auction> auctions = auctionService.searchActiveAuctions(query);

        //then
        assertIterableEquals(sortedList, auctions);
    }

    @Test
    public void getActiveAuctionsSortedListTest() {
        // given
        List<Auction> auctionsMock = new ArrayList<>();

        LocalDateTime before = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime after = LocalDateTime.of(2100, 1, 1, 0, 0);
        LocalDateTime after2 = LocalDateTime.of(2080, 1, 1, 0, 0);
        User user = new User();
        Image image = new Image();
        Auction auction = new Auction("asdf", "qwer", before, after, 100, user, image);
        Auction auction2 = new Auction("asdf", "qwer", before, after2, 100, user, image);

        auctionsMock.add(auction);
        auctionsMock.add(auction2);
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        List<Auction> sortedList = Arrays.asList(auction2, auction);

        //when
        List<Auction> auctions = auctionService.getActiveAuctions();

        //then
        assertIterableEquals(sortedList, auctions);
    }

    @Test
    public void getActiveAuctionsEmptyListTest() {
        // given
        List<Auction> auctionsMock = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);
        //when
        List<Auction> auctions = auctionService.getActiveAuctions();

        //then
        assertEquals(0, auctions.size());
    }

    @Test
    public void getActiveAuctionsTrueTest() {
        // given
        List<Auction> auctionsMock = new ArrayList<>();

        LocalDateTime before = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime after = LocalDateTime.of(2100, 1, 1, 0, 0);
        User user = new User();
        Image image = new Image();
        Auction auction = new Auction("asdf", "qwer", before, after, 100, user, image);

        auctionsMock.add(auction);
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        //when
        List<Auction> auctions = auctionService.getActiveAuctions();

        //then
        assertTrue(auctions.contains(auction));
    }

    @Test
    public void getActiveAuctionsFalseTest() {
        // given
        List<Auction> auctionsMock = new ArrayList<>();

        LocalDateTime before = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime after = LocalDateTime.of(2020, 1, 1, 0, 0);
        User user = new User();
        Image image = new Image();
        Auction auction = new Auction("asdf", "qwer", before, after, 100, user, image);

        auctionsMock.add(auction);
        Mockito.when(repository.findAll()).thenReturn(auctionsMock);

        //when
        List<Auction> auctions = auctionService.getActiveAuctions();

        //then
        assertFalse(auctions.contains(auction));
    }

    @Test
    public void getHighestBidForAuctionEmptyListTest() {
        //given
        final Long auctionId = 1L;
        List<Bid> bids = new ArrayList<>();
        Mockito.when(bidRepository.findAll()).thenReturn(bids);

        //when
        Optional<Bid> bid = auctionService.getHighestBidForAuction(auctionId);

        //then
        assertFalse(bid.isPresent());
    }

    @Test
    public void getHighestBidForAuctionDifferentAuctionTest() {
        //given
        final Long auctionId = 1L;
        List<Bid> bids = new ArrayList<>();

        User user = new User();
        Auction auction = new Auction();
        auction.setId(2L);

        Bid givenBid = new Bid(user, auction, 1000);
        bids.add(givenBid);

        Mockito.when(bidRepository.findAll()).thenReturn(bids);

        //when
        Optional<Bid> bid = auctionService.getHighestBidForAuction(auctionId);

        //then
        assertFalse(bid.isPresent());
    }

    @Test
    public void getHighestBidForAuctionSameAuctionTest() {
        //given
        final Long auctionId = 1L;
        List<Bid> bids = new ArrayList<>();

        User user = new User();
        Auction auction = new Auction();
        auction.setId(1L);

        Bid givenBid = new Bid(user, auction, 1000);
        bids.add(givenBid);

        Mockito.when(bidRepository.findAll()).thenReturn(bids);

        //when
        Optional<Bid> bid = auctionService.getHighestBidForAuction(auctionId);

        //then
        assertEquals(givenBid, bid.get());
    }

    @Test
    public void getHighestBidForAuctionMoreBidsTest() {
        //given
        final Long auctionId = 1L;
        List<Bid> bids = new ArrayList<>();

        User user = new User();
        Auction auction = new Auction();
        auction.setId(1L);
        Bid givenBid = new Bid(user, auction, 1000);
        Bid givenBid2 = new Bid(user, auction, 2000);
        Bid givenBid3 = new Bid(user, auction, 1500);


        bids.add(givenBid);
        bids.add(givenBid2);
        bids.add(givenBid3);

        Mockito.when(bidRepository.findAll()).thenReturn(bids);

        //when
        Optional<Bid> bid = auctionService.getHighestBidForAuction(auctionId);

        //then
        assertEquals(givenBid2, bid.get());
    }
}