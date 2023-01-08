package src.authenticationapi.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.authenticationapi.modules.user.dto.*;
import src.authenticationapi.modules.user.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserResponse> getAll() {
        return service.getAll().stream().map(UserResponse::of).collect(Collectors.toList());
    }

    @GetMapping(value = "{id}")
    public UserResponse getById(@PathVariable Long id) {
        return UserResponse.of(service.getById(id));
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        return UserResponse.of(service.create(request));
    }

    @PutMapping(value = "{id}")
    public UserResponse update(@RequestBody UserRequest request, @PathVariable Long id) {
        return UserResponse.of(service.update(request, id));
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
