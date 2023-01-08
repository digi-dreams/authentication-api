package src.authenticationapi.modules.user.model;

import org.junit.jupiter.api.Test;
import src.authenticationapi.modules.user.dto.UserResponse;
import src.authenticationapi.modules.user.enums.Eoccupation;
import java.time.*;

import static src.authenticationapi.modules.user.helper.UserHelper.tattooArtist;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void ofShouldReturnUser() {
        var response = UserResponse.of(tattooArtist(1l));

        assertThat(response)
                .extracting("id", "name", "cpf", "email",
                        "birthdate", "phone", "registrationData", "occupation")
                .containsExactly(1l, "Test", "12345678910", "test@test.com", LocalDate.now(),
                        "123456789", LocalDateTime.of(2017, Month.FEBRUARY,3,6,30,40,50000),
                        Eoccupation.TATTOO_ARTIST);
    }
}
