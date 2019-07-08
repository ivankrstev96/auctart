package com.auctart.web.dto;

import javax.validation.constraints.*;

public class BidDto {

    @NotNull
    @NotBlank
    public Long auctionId;

    @NotNull
    @NotBlank
    @PositiveOrZero
    public Integer price;
}
