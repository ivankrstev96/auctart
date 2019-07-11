package com.auctart.web.dto;

import javax.validation.constraints.*;

public class BidDto {

    @NotNull
    public Long auction;

    @NotNull
    @PositiveOrZero
    public Integer price;
}
