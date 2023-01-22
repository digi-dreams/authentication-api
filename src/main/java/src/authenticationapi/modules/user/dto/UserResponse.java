package src.authenticationapi.modules.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.BeanUtils;
import src.authenticationapi.modules.user.enums.Eoccupation;
import src.authenticationapi.modules.user.model.User;
import java.time.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    private String phone;
    private Eoccupation occupation;
    private LocalDateTime created_at;

    public static UserResponse of(User user) {
        var response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }
}

