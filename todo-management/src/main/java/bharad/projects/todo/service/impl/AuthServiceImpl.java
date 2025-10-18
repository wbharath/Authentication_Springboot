package bharad.projects.todo.service.impl;

import bharad.projects.todo.dto.LoginDto;
import bharad.projects.todo.dto.RegisterDto;
import bharad.projects.todo.entity.Role;
import bharad.projects.todo.entity.User;
import bharad.projects.todo.exception.TodoAPIEXception;
import bharad.projects.todo.repository.RoleRepository;
import bharad.projects.todo.repository.UserRepository;
import bharad.projects.todo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    @Override
    public String register(RegisterDto registerdto) {
        //check username is already existing in DB
        if(userRepository.existsByUsername(registerdto.getUsername())) {
            throw new TodoAPIEXception(HttpStatus.BAD_REQUEST, "username already exists!!");

        }
//        check email is already existing in DB
        if(userRepository.existsByEmail(registerdto.getEmail())) {
            throw new TodoAPIEXception(HttpStatus.BAD_REQUEST, "email already exists!!");
        }


        User user = new User();
        user.setName(registerdto.getName());
        user.setUsername(registerdto.getUsername());
        user.setEmail(registerdto.getEmail());
        user.setPassword(passwordEncoder.encode(registerdto.getPassword()));

        Set<Role> role = new HashSet<>();
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        role.add(userRole);
        user.setRoles(role);
        userRepository.save(user);
        return "User has Registered Succesfully!!";
    }

    @Override
    public String login(LoginDto logindto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                logindto.getUsernameOrEmail(),
                logindto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User Logoed in Successfully!!";
    }
}
