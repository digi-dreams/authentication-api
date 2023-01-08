package src.authenticationapi.modules.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import src.authenticationapi.modules.user.enums.Eoccupation;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    private String phone;
    @NotBlank
    private String password;
    @NotBlank  @Enumerated(EnumType.STRING)
    private Eoccupation occupation;
}
