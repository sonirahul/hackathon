package com.donateknowledge;

import com.donateknowledge.model.Publisher;
import com.donateknowledge.service.CountryService;
import com.donateknowledge.service.PublisherService;
import com.donateknowledge.service.StateService;
import java.util.Map;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @RequestMapping("/managePublishers")
    public String showManagePublishers(Map<String, Object> map) {
        map.put("publisher", new Publisher());
        map.put("publisherList", publisherService.listPublisher());
        map.put("countryList", countryService.listCountry());
        map.put("stateList", stateService.listState());
        return "managePublishers";
    }

    @RequestMapping(value = "/savePublisher", method = RequestMethod.POST)
    public String savePublisher(Map<String, Object> map, @ModelAttribute("publisher") Publisher publisher, @Valid Publisher publisherValid, BindingResult result) {
        if (result.hasErrors()) {
            map.put("countryList", countryService.listCountry());
            map.put("stateList", stateService.listState());
            return "managePublishers";
        } else {
            try {
                publisherService.savePublisher(publisher);
                return "redirect:/admin/managePublishers";
            } catch (ConstraintViolationException exp) {
                map.put("dbError", exp.getMessage());
                return "managePublishers";
            }
        }
    }

    @RequestMapping("/deletePublisher/{publisherNo}")
    public String deletePublisher(Map<String, Object> map, @PathVariable("publisherNo") Integer publisherNo) {
        try {
            publisherService.removePublisher(publisherNo);
            return "redirect:/admin/managePublishers";
        } catch (DataIntegrityViolationException exp) {
            map.put("dbError", "Cannot delete a parent row.");
            map.put("publisher", new Publisher());
            return "managePublishers";
        }
    }
    
    @RequestMapping("/editPublisher/{publisherNo}")
    public String editPublisher(Map<String, Object> map, @PathVariable("publisherNo") Integer publisherNo) {
        map.put("publisher", publisherService.getPublisherById(publisherNo));
        map.put("countryList", countryService.listCountry());
        map.put("stateList", stateService.listState());
        return "managePublishers";
    }
}
