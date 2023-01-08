package src.authenticationapi.modules.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.authenticationapi.modules.common.exception.NotFoundException;
import src.authenticationapi.modules.common.exception.ValidationException;
import src.authenticationapi.modules.user.dto.UserRequest;
import src.authenticationapi.modules.user.model.User;
import src.authenticationapi.modules.user.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    public static final NotFoundException USER_NOT_FOUND = new NotFoundException("USER ID NOT FOUND.");
    public static final ValidationException USER_ALREADY_EXISTS = new ValidationException("USER ALREADY EXISTS.");

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Long id) {
        return validationFindById(id);
    }

    @Transactional
    public User create(UserRequest request) {
        validationUserExists(request);
        return repository.save(User.of(request));
    }

    @Transactional
    public User update(UserRequest request, Long id) {
        var user = validationFindById(id);
        BeanUtils.copyProperties(request, user, "id");
        return user;
    }

    @Transactional
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw USER_NOT_FOUND;
        }
    }

    public User validationFindById(Long id) {
        return repository.findById(id).orElseThrow(() -> USER_NOT_FOUND);
    }

    public void validationUserExists(UserRequest request) {
        if (repository.existsByCpfOrEmail(request.getCpf(), request.getEmail())) {
            throw USER_ALREADY_EXISTS;
        }
    }
}
