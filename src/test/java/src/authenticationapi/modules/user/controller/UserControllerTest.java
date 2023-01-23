package src.authenticationapi.modules.user.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import src.authenticationapi.modules.user.model.User;
import src.authenticationapi.modules.user.service.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static src.authenticationapi.helper.TestsHelper.convertObjectToJsonBytes;
import static src.authenticationapi.modules.user.helper.UserHelper.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@ActiveProfiles("dev")
public class UserControllerTest {

    private static final String URL = "/api/users";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService service;

    @Test
    @SneakyThrows
    public void getAllShouldReturnIsOk() {
        mvc.perform(get(URL))
                .andExpect(status().isOk());

        verify(service).getAll();
    }

    @Test
    @SneakyThrows
    public void getByIdShouldReturnIsOk() {
        when(service.getById(1l)).thenReturn(tattooArtist(1l));

        mvc.perform(get(URL + "/{id}", 1l)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).getById(1l);
    }

    @Test
    @SneakyThrows
    public void createShouldReturnIsOk() {
        when(service.create(tattooArtistRequestWithoutId())).thenReturn(User.of(tattooArtistRequestWithoutId()));

        mvc.perform(post(URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(convertObjectToJsonBytes(tattooArtistRequestWithoutId())))
                .andExpect(status().isOk());

        verify(service).create(eq(tattooArtistRequestWithoutId()));
    }

    @Test
    @SneakyThrows
    public void deleteShouldReturnIsOk() {
        when(service.getById(1l)).thenReturn(tattooArtist(1l));

        mvc.perform(delete(URL + "/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service).delete(1l);
    }
}
