package com.auctart.service;

import com.auctart.domain.Image;
import com.auctart.repository.ImageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
public class ImageServiceTest {

    @MockBean
    ImageRepository repository;
    @MockBean
    ImageService imageService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageService(repository);
    }

    @Test
    public void findImageGoodTest() {
        //given
        final Long id = 1L;
        Image image = new Image();
        image.setBytes(new Byte[]{});

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(image));

        //when
        Optional<Byte[]> bytes = imageService.findImage(id);

        //then
        assertEquals(image.getBytes(), bytes.get());
    }

    @Test
    public void findImageBadTest() {
        //given
        final Long id = 1L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        //when
        Optional<Byte[]> bytes = imageService.findImage(id);

        //then
        assertFalse(bytes.isPresent());
    }

}