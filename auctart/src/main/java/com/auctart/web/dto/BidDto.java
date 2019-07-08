package com.auctart.web.dto;

import javax.validation.constraints.*;

public class BidDto {

    @NotNull
    public Long auctionId;

    @NotNull
    @PositiveOrZero
    public Integer price;
}
