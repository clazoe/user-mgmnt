package com.canvia.usermgmnt;

import com.canvia.usermgmnt.dto.LoginUserDto;
import com.canvia.usermgmnt.dto.RegisterUserDto;
import com.canvia.usermgmnt.service.AuthenticationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class UserMgmntApplicationTest {

	private static final Gson GSON = new GsonBuilder().create();

	@Autowired
	private AuthenticationService applicationUserService;
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void insertData() {
		applicationUserService.crearUsuarioRol(buildMockUser("ADMINISTRADOR"));
	}

	private RegisterUserDto buildMockUser(String rol){
		RegisterUserDto input = new RegisterUserDto();
		input.setCorreo("Test12345@mail.com");
		input.setNombreUsuario("Test12345");
		input.setPassword("Test12345");
		input.setRol(rol);
		return input;
	}

	@Test
	@DisplayName("Return bearer code when registered user is valid")
	void returnBearerCodeWhenRegisteredUserIsValid() throws Exception {
		String userJSON = GSON.toJson(buildMockUser("ADMINISTRADOR"));
		MvcResult mvcResult = mockMvc.perform(post("/loginUsuario").content(userJSON)).andReturn();
		String bearer = mvcResult.getResponse().getContentAsString();
		assertThat(!bearer.isEmpty());
	}

	@Test
	@DisplayName("Return exception when create user with invalid role")
	void returnExceptionWhenCreateUserWithInvalidRole() {
		assertThrows(IllegalArgumentException.class,
				()->{
					applicationUserService.crearUsuarioRol(buildMockUser("INVITADO"));
				});
	}

	@Test
	@DisplayName("Return user when authentication is successful")
	void returnUserWhenAuthenticationIsSuccessful() {
		LoginUserDto login = new LoginUserDto();
		login.setNombreUsuario("Test12345");
		login.setPassword("Test12345");
		assertNotNull(applicationUserService.authenticate(login));
	}


	@Test
	@DisplayName("Return user list when invocation is Ok")
	void returnUserListWhenInvocationIsOk() {
		assertNotNull(applicationUserService.allUsers());
	}


}
