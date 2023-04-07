package nur.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SmallController {

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

}