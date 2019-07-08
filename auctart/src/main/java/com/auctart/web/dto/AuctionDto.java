package com.auctart.web.dto;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class AuctionDto {
    @NotNull
    @NotBlank
    public String name;
    @NotNull
    @NotBlank
    public String author;

    @NotNull
    @NotBlank
    @Future
    public LocalDateTime endDate;

    @NotNull
    @NotBlank
    @PositiveOrZero
    public Integer startPrice;
}
