package dbp.week5.asesoriaeventos.User.application;


import dbp.week5.asesoriaeventos.User.domain.User;
import dbp.week5.asesoriaeventos.User.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<Iterable<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.status(201).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> parcialUpdate(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.parcialUpdate(id, user));
    }

}
