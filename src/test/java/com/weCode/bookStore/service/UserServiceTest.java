package com.weCode.bookStore.service;

import com.weCode.bookStore.dto.UserDto;
import com.weCode.bookStore.model.User;
import com.weCode.bookStore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void shouldReturnUserIdWhenCalledWithUserData() {
        when(userRepository.saveAndFlush(any())).thenReturn(getUser());
        when(modelMapper.map(any(),any())).thenReturn(getUser());

        UUID id = userService.addUser(getUserDto());

        assertThat(id).isNotNull();
    }

    @Test
    public void shouldReturnUserWhenEmailExist() {
        when(userRepository.findByEmail(anyString())).thenReturn(getUser());
        when(modelMapper.map(any(),any())).thenReturn(getUserDto());

        UserDto email = userService.getUserByEmail("email");

        assertThat(email).isNotNull();
        assertThat(email.getName()).isEqualTo("username");
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsNotExist() {
        when(userRepository.findByEmail(anyString())).thenThrow(new RuntimeException("not exist!"));

        assertThatThrownBy(() -> userService.getUserByEmail("invalidEmail")).isInstanceOf(RuntimeException.class);
    }

    private UserDto getUserDto() {
        return UserDto.builder()
                .email("email")
                .name("username")
                .password("password")
                .build();
    }

    private User getUser() {
        return User.builder()
                .email("email")
                .name("username")
                .id(UUID.randomUUID())
                .password("password")
                .build();
    }

}