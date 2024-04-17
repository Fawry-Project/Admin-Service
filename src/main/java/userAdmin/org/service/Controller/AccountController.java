package userAdmin.org.service.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import userAdmin.org.service.Model.User;
import userAdmin.org.service.Service.UserService;


@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/test")
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

  @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.ViewAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") int id) {
        Optional<User> user = userService.FindUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/activate/{userId}")
    public ResponseEntity<String> activateUser(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(userService.activateUser(userId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/deactivate/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable(name = "userId") int userId){
        return new ResponseEntity<>(userService.deactivateUser(userId), HttpStatus.ACCEPTED);
    }
}
