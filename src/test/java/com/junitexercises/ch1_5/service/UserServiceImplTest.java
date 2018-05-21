package com.junitexercises.ch1_5.service;

import com.junitexercises.ch1_5.dao.UserDAO;
import com.junitexercises.ch1_5.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private User user = mock(User.class);
    private UserDAO userDAO = mock(UserDAO.class);
    private SecurityService securityService = mock(SecurityService.class);
    private static final String DEFAULT_PASSWORD = "password";
    private static final String DEFAULT_PASSWORD_MD5 = "passwordmd5";

    @Test
    public void assignPassword_HappyPathTest_SendUpdateToUser() {
        when(user.getPassword()).thenReturn(DEFAULT_PASSWORD);
        when(securityService.md5(DEFAULT_PASSWORD)).thenReturn(DEFAULT_PASSWORD_MD5);
        UserServiceImpl userService = new UserServiceImpl(userDAO, securityService);
        try {
            userService.assignPassword(user);
            verify(user).setPassword(DEFAULT_PASSWORD_MD5);
            verify(userDAO).updateUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}