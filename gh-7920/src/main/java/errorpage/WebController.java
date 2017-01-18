package errorpage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @RequestMapping("/test")
    public void test() {
        throw new RuntimeException("sample exception");
    }

    @RequestMapping("/redirect")
    public String redirect() {
        return "Redirect output";
    }

}
