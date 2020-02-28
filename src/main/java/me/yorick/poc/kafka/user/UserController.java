package me.yorick.poc.kafka.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/publish")
    public String publish()  {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("yorick");
        user.setAge(41);
        user.setGender(Gender.Male);
        try {
            return userService.publishMessage(user);
        } catch (ExecutionException|InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @GetMapping("/get")
    public User get() {
        return userService.getOne();
    }
}
