package sciuto.corey.lcwdb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QueryController {

    private static final Logger LOGGER = Logger.getLogger(QueryController.class);

    public QueryController() {
    }
    
    @RequestMapping(value = "/test")
    public ModelAndView testView(){
        return new ModelAndView("test");
    }
}
