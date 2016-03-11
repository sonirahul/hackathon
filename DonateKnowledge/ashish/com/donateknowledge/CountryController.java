package com.donateknowledge;

import com.donateknowledge.model.Country;
import com.donateknowledge.service.CountryService;
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
public class CountryController {
    @Autowired
    private CountryService countryService;

    @RequestMapping("/manageCountries")
    public String showManageCountries(Map<String, Object> map) {
        map.put("country", new Country());
        map.put("countryList", countryService.listCountry());
        return "manageCountries";
    }

    @RequestMapping(value = "/saveCountry", method = RequestMethod.POST)
    public String saveCountry(Map<String, Object> map, @ModelAttribute("country") Country country, @Valid Country countryValid, BindingResult result) {
        if (result.hasErrors()) {
            return "manageCountries";
        } else {
            try {
                countryService.saveCountry(country);
                return "redirect:/admin/manageCountries";
            } catch (ConstraintViolationException exp) {
                map.put("dbError", exp.getMessage());
                return "manageCountries";
            }
        }
    }

    @RequestMapping("/deleteCountry/{countryNo}")
    public String deleteCountry(Map<String, Object> map, @PathVariable("countryNo") Integer countryNo) {
        try {
            countryService.removeCountry(countryNo);
            return "redirect:/admin/manageCountries";
        } catch (DataIntegrityViolationException exp) {
            map.put("dbError", "Cannot delete a parent row.");
            map.put("country", new Country());
            return "manageCountries";
        }
    }
    
    @RequestMapping("/editCountry/{countryNo}")
    public String editCountry(Map<String, Object> map, @PathVariable("countryNo") Integer countryNo) {
        map.put("country", countryService.getCountryById(countryNo));
        return "manageCountries";
    }
}
