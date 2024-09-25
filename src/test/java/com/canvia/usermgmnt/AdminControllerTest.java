package com.canvia.usermgmnt;

import com.canvia.usermgmnt.controller.AdminController;
import com.canvia.usermgmnt.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import com.canvia.usermgmnt.dto.RegisterUserDto;
import static org.junit.jupiter.api.Assertions.assertTrue;



@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private UserService userService;

    @Test
    @DisplayName("Return eliminaUsuario status when user is removed")
    void returnEliminaUsuarioStatusWhenUserIsRemoved(){
        Map<String, Boolean> retrunMap = adminController.eliminarUsuario("test");
        assertTrue(retrunMap.containsKey("eliminaUsuarioStatus"));
    }

    @Test
    @DisplayName("Return actualizacion status when user is updated")
    void returnActualizacionStatusWhenUserIsUpdated(){
        String nombreUsuario = "test";
        RegisterUserDto dto = new RegisterUserDto();
        dto.setNombreUsuario("test");
        dto.setCorreo("test");
        dto.setPassword("test");
        dto.setRol("ADMINISTRADOR");
        Map<String, Boolean> retrunMap = adminController.actualizaUsuario(nombreUsuario,dto);
        assertTrue(retrunMap.containsKey("actualizacionStatus"));
    }
}
