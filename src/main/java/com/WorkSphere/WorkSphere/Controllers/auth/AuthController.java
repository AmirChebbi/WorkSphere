package com.WorkSphere.WorkSphere.Controllers.auth;



import com.WorkSphere.WorkSphere.DTOs.auth.LoginDTO;
import com.WorkSphere.WorkSphere.DTOs.auth.RegisterDTO;
import com.WorkSphere.WorkSphere.Services.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO registerDto)
    {
        return authService.register(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object>  login(@Valid @RequestBody LoginDTO loginDto)
    {
        return authService.login(loginDto);
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") final String token)
    {
        return authService.confirmToken(token);
    }

    @GetMapping("/refresh/{refreshToken}")
    public ResponseEntity<Object> renewAccessToken(@RequestParam("expiredToken") final String expiredToken, @PathVariable("refreshToken") final String refreshToken)
    {
        return authService.renewAccessToken(refreshToken,expiredToken);
    }


}