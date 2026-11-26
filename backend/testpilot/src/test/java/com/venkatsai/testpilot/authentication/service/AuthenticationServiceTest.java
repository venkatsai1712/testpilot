package com.venkatsai.testpilot.authentication.service;

import com.venkatsai.testpilot.authentication.dto.request.LoginRequest;
import com.venkatsai.testpilot.authentication.dto.request.RegisterRequest;
import com.venkatsai.testpilot.authentication.dto.response.UserResponse;
import com.venkatsai.testpilot.authentication.entity.User;
import com.venkatsai.testpilot.authentication.exception.InvalidCredentialsException;
import com.venkatsai.testpilot.authentication.exception.InvalidEmailException;
import com.venkatsai.testpilot.authentication.exception.InvalidPasswordException;
import com.venkatsai.testpilot.authentication.exception.InvalidUserNameException;
import com.venkatsai.testpilot.authentication.repository.AuthenticationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @Mock
    private  AuthenticationRepository authenticationRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testRegisterRequest() {
        RegisterRequest req = new RegisterRequest("Abc","abc@gmail.com","12345678");
        User user = new User("Abc", "abc@gmail.com", "12345678");
        when(authenticationRepository.save(any(User.class))).thenReturn(user);

        UserResponse res = authenticationService.register(req);

        assertEquals("Abc", res.name());
        assertEquals("abc@gmail.com", res.email());;
        assertThrows(InvalidUserNameException.class, () -> authenticationService.register(new RegisterRequest("","abc@gmail.com","12345678")));
        assertThrows(InvalidEmailException.class, () -> authenticationService.register(new RegisterRequest("Abc","abcgmail.com","12345678")));
        assertThrows(InvalidPasswordException.class, () -> authenticationService.register(new RegisterRequest("Abc","abc@gmail.com","15678")));
    }

    @Test
    public void testLoginRequest() {
        LoginRequest req = new LoginRequest("abc@gmail.com","12345678");
        User user = new User("Abc", "abc@gmail.com", "12345678");
        when(authenticationRepository.findByEmail(any(String.class))).thenReturn(user);

        UserResponse res = authenticationService.login(req);

        assertEquals("abc@gmail.com", res.email());;
        assertThrows(InvalidEmailException.class, () -> authenticationService.login(new LoginRequest("abcgmail.com","12345678")));
        assertThrows(InvalidPasswordException.class, () -> authenticationService.login(new LoginRequest("abc@gmail.com","15678")));
        assertThrows(InvalidCredentialsException.class, () -> authenticationService.login(new LoginRequest("abc@gmail.com","123456788")));
        assertThrows(InvalidCredentialsException.class, () -> authenticationService.login(new LoginRequest("ab@gmail.com","123456788")));

    }

}
