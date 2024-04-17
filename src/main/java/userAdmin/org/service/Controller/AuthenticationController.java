package userAdmin.org.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import userAdmin.org.service.Authentication.AuthenticationRequest;
import userAdmin.org.service.Authentication.AuthenticationResponse;
import userAdmin.org.service.Authentication.AuthenticationService;
import userAdmin.org.service.Authentication.RegisterRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {
  @Autowired
    private final AuthenticationService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register( @RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
