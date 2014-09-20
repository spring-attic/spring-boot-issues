package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class Ctrl {
    private final static Logger LOGGER = LoggerFactory.getLogger(Ctrl.class);

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String ctrl() {
    LOGGER.debug("Ping!");
    return "Ping!";
  }
}
