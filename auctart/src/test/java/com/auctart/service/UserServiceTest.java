package com.auctart.service;

import com.auctart.domain.Roles;
import com.auctart.domain.User;
import com.auctart.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    UserRepository repository;
    @MockBean
    UserService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        service = new UserService(repository);
    }

    @Test
    public void getByUsernameGoodTest() {
        //given
        final String username = "asdf";
        User givenUser = new User("asdf", "123", "test@test.com", Roles.USER);

        Mockito.when(repository.findByUserName("asdf")).thenReturn(Optional.of(givenUser));

        //when
        User user = service.getByUsername(username);

        //then
        assertEquals(givenUser, user);
    }

    @Test
    public void getByUsernameBadTest() {
        //given
        final String username = "asdf";

        Mockito.when(repository.findByUserName("asdf")).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(UsernameNotFoundException.class,() -> service.getByUsername(username));
    }

}