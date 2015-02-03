package org.springframework.boot.issues.api.socket;

import org.springframework.boot.issues.api.socket.rep.Greeting;
import org.springframework.boot.issues.api.socket.rep.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping(value="/hellorest", method = RequestMethod.POST)
    public void greet(@RequestBody HelloMessage message) {
        Greeting greeting = new Greeting("Hello, " + message.getName() + "!");
        simpMessagingTemplate.convertAndSend("/topic/greetings", greeting);
    }
}
