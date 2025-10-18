package bharad.projects.todo.controller;


import bharad.projects.todo.dto.LoginDto;
import bharad.projects.todo.dto.RegisterDto;
import bharad.projects.todo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

//    Build register REST API

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response  = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response  = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
