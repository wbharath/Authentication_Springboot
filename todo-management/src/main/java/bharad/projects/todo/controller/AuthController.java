package bharad.projects.todo.controller;


import bharad.projects.todo.dto.JwtAuthResponse;
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
@CrossOrigin("*")
public class AuthController {
    private AuthService authService;

//    Build register REST API

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response  = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        // System.out.println("=== BACKEND RECEIVED ===");
        // System.out.println("UsernameOrEmail: '" + loginDto.getUsernameOrEmail() + "'");
        // System.out.println("Password: '" + loginDto.getPassword() + "'");
        // System.out.println("UsernameOrEmail length: " + (loginDto.getUsernameOrEmail() != null ? loginDto.getUsernameOrEmail().length() : "null"));
        // System.out.println("Password length: " + (loginDto.getPassword() != null ? loginDto.getPassword().length() : "null"));

        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }




}
