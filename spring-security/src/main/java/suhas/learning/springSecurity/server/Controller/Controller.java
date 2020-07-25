package suhas.learning.springSecurity.server.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    String getWelcomeAllMessage() {
        return "<h1>Hello! I am for all</h1>";
    }

    @GetMapping("/user")
    String getWelcomeUserMessage() {
        return "<h1>Hello! you should me min User</h1>";
    }

    @GetMapping("/admin")
    String getWelcomeAdminMessage() {
        return "<h1>Hello! you should me min Admin</h1>";
    }
}
