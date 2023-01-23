package src.authenticationapi.modules.user.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import src.authenticationapi.modules.user.dto.UserRequest;
import src.authenticationapi.modules.user.enums.Eoccupation;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id")
@EntityListeners(AuditingEntityListener.class)
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
    @Column(name = "created_at", updatable = false, nullable = false) @CreatedDate
    private LocalDateTime created_at;
    @Column(name = "occupation")
    @Enumerated(EnumType.STRING)
    private Eoccupation occupation;

    public static User of(UserRequest request) {
        var user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }
}
