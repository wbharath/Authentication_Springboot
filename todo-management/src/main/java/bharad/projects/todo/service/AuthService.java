package bharad.projects.todo.service;

import bharad.projects.todo.dto.LoginDto;
import bharad.projects.todo.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerdto);

    String login(LoginDto logindto);

}
