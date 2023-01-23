package src.authenticationapi.modules.user.helper;

import src.authenticationapi.modules.user.dto.UserRequest;
import src.authenticationapi.modules.user.enums.Eoccupation;
import src.authenticationapi.modules.user.model.User;
import java.time.*;
import java.util.List;

public class UserHelper {

    public static User tattooArtist(Long id) {
        return User.builder()
                .id(id)
                .name("Test")
                .cpf("12345678910")
                .email("test@test.com")
                .birthdate(LocalDate.now())
                .phone("123456789")
                .password("test")
                .created_at(LocalDateTime.of(2017,Month.FEBRUARY,3,6,30,40,50000))
                .occupation(Eoccupation.TATTOO_ARTIST)
                .build();
    }

    public static UserRequest tattooArtistRequest(Long id) {
        return UserRequest.builder()
                .id(id)
                .name("Test")
                .cpf("12345678910")
                .email("test@test.com")
                .birthdate(LocalDate.now())
                .phone("123456789")
                .password("test")
                .occupation(Eoccupation.TATTOO_ARTIST)
                .build();
    }

    public static UserRequest tattooArtistRequestWithoutId() {
        return UserRequest.builder()
                .name("Test")
                .cpf("12345678910")
                .email("test@test.com")
                .birthdate(LocalDate.now())
                .phone("123456789")
                .password("test")
                .occupation(Eoccupation.TATTOO_ARTIST)
                .build();
    }

    public static List<User> tattooArtistList() {
        return List.of(
                tattooArtist(1l),
                tattooArtist(2l),
                tattooArtist(3l)
        );
    }
}
