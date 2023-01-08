package src.authenticationapi.modules.user.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.BeanUtils;
import src.authenticationapi.modules.user.dto.UserRequest;
import src.authenticationapi.modules.user.enums.Eoccupation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cpf", length = 14)
    private String cpf;
    @Column(name = "email")
    private String email;
    @Column(name = "birth_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    @Column(name = "phone")
    private String phone;
    @JsonIgnore
    @Column(name = "password", nullable = false, updatable = false, length = 80)
    @NotBlank
    private String password;
    @Column(name = "registration_data", updatable = false, nullable = false)
    private LocalDateTime registrationData;
    @Column(name = "occupation")
    @Enumerated(EnumType.STRING)
    private Eoccupation occupation;

    public static User of(UserRequest request) {
        var user = new User();
        BeanUtils.copyProperties(request, user);
        user.setRegistrationData(LocalDateTime.now());
        return user;
    }
}
