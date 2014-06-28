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

import sciuto.corey.lcwdb.model.*;
import sciuto.corey.lcwdb.services.DataService;
import sciuto.corey.lcwdb.services.QueryResultList;

@Controller
@SessionAttributes({ "queryByName", "queryByNameResults", "cemeteries", "sortBy" })
// We don't want to lose these from the model when changing pages.
public class QueryController {

    private static final Logger LOGGER = Logger.getLogger(QueryController.class);

    @Autowired
    private DataService dataService;

    /**
     * The list of cemeteries. We want this stored in once place.
     */
    private List<Cemetery> cemeteries = null;
    private final int NULL_CEMETERY_ID = -1;

    private List<SortBy> sortBy;

    public QueryController() {
        sortBy = new ArrayList<SortBy>();

        SortBy nameAsc = new SortBy();
        nameAsc.setSortType(SortBy.SortType.NAME_ASC);
        SortBy nameDesc = new SortBy();
        nameDesc.setSortType(SortBy.SortType.NAME_DESC);

        SortBy dodAsc = new SortBy();
        dodAsc.setSortType(SortBy.SortType.DOD_ASC);
        SortBy dodDesc = new SortBy();
        dodDesc.setSortType(SortBy.SortType.DOD_DESC);

        sortBy.add(nameAsc);
        sortBy.add(nameDesc);
        sortBy.add(dodAsc);
        sortBy.add(dodDesc);
    }
    
    @RequestMapping(value = "/test")
    public ModelAndView testView(){
        return new ModelAndView("test");
    }

    /**
     * The landing page. Display an empty form.
     * 
     * @param queryByName
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView firstPage(QueryByName queryByName) {
        LOGGER.debug("Serving /");

        if (cemeteries == null) {
            LOGGER.debug("Initializing list of cemeteries...");
            cemeteries = new ArrayList<Cemetery>();
            Cemetery empty = new Cemetery();
            empty.setId(NULL_CEMETERY_ID);
            empty.setName("ALL");
            cemeteries.add(empty);
            cemeteries.addAll(dataService.getCemeteries());
        }

        ModelAndView model = new ModelAndView("home");
        model.getModel().put("queryByName", queryByName);
        model.getModel().put("cemeteries", cemeteries);
        model.getModel().put("sortBy", sortBy);
        return model;
    }

    /**
     * If somebody does a GET on the POST URL, just kick them back.
     * 
     * @param queryByName
     * @return
     */
    @RequestMapping(value = "/queryByName", method = RequestMethod.GET)
    public ModelAndView getOnQueryByName(@ModelAttribute("queryByName") QueryByName queryByName) {
        return firstPage(queryByName);
    }

    /**
     * The main request method for searching by soldier name and/or cemetery.
     * 
     * @param queryByName
     *            Must be Valid.
     * @return
     */
    @RequestMapping(value = "/queryByName", method = RequestMethod.POST)
    public ModelAndView queryByName(@Valid @ModelAttribute("queryByName") QueryByName queryByName, BindingResult bindingResult) {
        LOGGER.debug("Serving /queryByName");

        if (bindingResult.hasErrors()) {
            for (ObjectError e : bindingResult.getAllErrors()) {
                LOGGER.error("Binding Error: " + e.getDefaultMessage());
            }
            ModelAndView model = new ModelAndView("home");
            // Put back the existing model so we don't lose it.
            model.getModel().put("queryByName", queryByName);
            model.getModel().put("cemeteries", cemeteries);
            model.getModel().put("sortBy", sortBy);
            return model;
        }

        if (queryByName.getCemeteryId().equals(NULL_CEMETERY_ID)) {
            queryByName.setCemeteryId(null);
        }

        ModelAndView model = new ModelAndView("home");

        QueryResultList<QueryByNameResultRecord> resultList = dataService.queryByName(queryByName);

        // Put back the existing model so we don't lose it.
        model.getModel().put("queryByNameResults", resultList);
        model.getModel().put("queryByName", queryByName);
        model.getModel().put("cemeteries", cemeteries);
        model.getModel().put("sortBy", sortBy);
        return model;
    }

    /**
     * Displays the soldier detail page.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/soldier", method = RequestMethod.GET)
    public ModelAndView queryBySoldier(@RequestParam(value = "id", required = true) Integer id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Serving /soldier?" + id);
        }

        ModelAndView model = new ModelAndView("soldier");
        SoldierResultRecord resultRecord = dataService.querySoldier(id);
        model.getModel().put("soldierRecord", resultRecord);
        return model;
    }

    /*
     * @RequestMapping(value="/404", method=RequestMethod.GET) public ModelAndView notFound(HttpServletRequest req) {
     * LOGGER.warn("A page request was not found.");
     * 
     * return new ModelAndView("404"); }
     */

    /**
     * Once we've left the controller, this handles exceptions redirected here by the servlet...
     * 
     * @param req
     * @param exception
     * @return
     */
    /*
     * @RequestMapping(value={"/500"}, method=RequestMethod.GET) public ModelAndView
     * escapedServerError(HttpServletRequest req, Exception exception) { return defaultExceptionHandler(req,exception);
     * }
     */

    /*
     * @ExceptionHandler(HttpSessionRequiredException.class) public ModelAndView handleNoSession(){ return new
     * ModelAndView("redirect:/"); }
     */

    /*
     * @ExceptionHandler(IllegalArgumentException.class) public ModelAndView handleIllegalArgument(){ return new
     * ModelAndView("redirect:/404"); }
     */

    /*
     * @ExceptionHandler(Exception.class) public ModelAndView defaultExceptionHandler(HttpServletRequest req, Exception
     * exception) {
     * 
     * String request = req != null ? req.getRequestURL().toString() : "unknown request"; String exceptionMsg =
     * exception != null ? exception.getMessage() : "unknown exception"; LOGGER.error("Request: " + request + " raised "
     * + exceptionMsg, exception);
     * 
     * return new ModelAndView("error"); }
     */

    public List<Cemetery> getCemeteries() {
        return cemeteries;
    }

    public void setCemeteries(List<Cemetery> cemeteries) {
        this.cemeteries = cemeteries;
    }

    public DataService getDataService() {
        return dataService;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

}
