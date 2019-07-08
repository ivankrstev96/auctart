package com.auctart.web.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
    @Future
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime endDate;

    @NotNull
    @PositiveOrZero
    public Integer startPrice;

    @NotNull
    public MultipartFile image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
