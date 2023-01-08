package src.authenticationapi.modules.user.dto;

import org.junit.jupiter.api.Test;
import src.authenticationapi.modules.user.enums.Eoccupation;
import src.authenticationapi.modules.user.model.User;
import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;
import static src.authenticationapi.modules.user.helper.UserHelper.tattooArtistRequest;

public class UserResponseTest {

    @Test
    public void ofShouldReturnUserResponse() {
        var request = User.of(tattooArtistRequest(1l));

        assertThat(request)
                .extracting("id", "name", "cpf", "email",
                        "birthdate", "phone", "occupation")
                .containsExactly(1l, "Test", "12345678910", "test@test.com", LocalDate.now(),
                        "123456789", Eoccupation.TATTOO_ARTIST);
    }
}
