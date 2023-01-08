package src.authenticationapi.modules.user.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import src.authenticationapi.modules.user.model.User;
import src.authenticationapi.modules.user.repository.UserRepository;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static src.authenticationapi.modules.user.helper.UserHelper.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;

    @Test
    public void createShouldCreateUser() {
        var user = tattooArtistRequest(1l);

        when(repository.save(User.of(user))).thenReturn(User.of(user));

        var savedUser = service.create(user);

        assertThat(savedUser).isNotNull();
        verify(repository).save(savedUser);
    }

    @Test
    public void updateShouldUpdateUser() {
        var user = tattooArtistRequest(1l);

        when(repository.findById(user.getId())).thenReturn(Optional.of(User.of(user)));

        var expected = service.update(user, 1l);

        assertThat(expected).isNotNull();
        verify(repository).findById(expected.getId());
    }

    @Test
    public void getAllShouldReturnAllUser() {
        var users = tattooArtistList();

        when(repository.findAll()).thenReturn(users);

        var expected = service.getAll();

        assertEquals(expected, users);
    }

    @Test
    public void getByIdShouldReturnUser() {
        var user = tattooArtist(1l);

        when(repository.findById(user.getId())).thenReturn(Optional.of(user));

        var expected = service.getById(user.getId());

        assertEquals(expected, user);
    }

    @Test
    public void deleteShouldDeleteUser() {
        service.delete(1l);

        verify(repository).deleteById(1l);
    }
}
