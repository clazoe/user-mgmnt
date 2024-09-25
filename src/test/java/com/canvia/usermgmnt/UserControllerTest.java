package com.canvia.usermgmnt;

import com.canvia.usermgmnt.controller.UserController;
import com.canvia.usermgmnt.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;

    @Test
    @DisplayName("Return usuarios when data exists")
    void returnUsuariosWhenDataExists(){
        assertNotNull(userController.usuariosList());
    }
}
