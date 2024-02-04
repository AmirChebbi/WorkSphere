package com.WorkSphere.WorkSphere.Services.Authentication;



import com.WorkSphere.WorkSphere.DTOs.Authentication.LoginDTO;
import com.WorkSphere.WorkSphere.DTOs.Authentication.RegisterDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<Object> register(@NotNull final RegisterDTO registerDto) ;
    public ResponseEntity<Object>  login(@NotNull final LoginDTO loginDto);
    public ResponseEntity<Object> renewAccessToken(final String refreshToken, final String expiredToken);
    public String confirmToken(final String token);
    public ResponseEntity<Object> test();

}
