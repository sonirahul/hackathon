package com.donateknowledge;

import com.donateknowledge.model.Search;
import com.donateknowledge.model.User;
import com.donateknowledge.service.CountryService;
import com.donateknowledge.service.MailService;
import com.donateknowledge.service.StateService;
import com.donateknowledge.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {
    @Value("${emailFrom}")
    String emailFrom;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @RequestMapping("/signUp")
    public String showSignUp(Map<String, Object> map) {
        map.put("user", new User());
        map.put("countryList", countryService.listCountry());
        map.put("stateList", stateService.listState());
        map.put("search", new Search());
        return "signUp";
    }

    @RequestMapping(value = "/saveSignUp", method = RequestMethod.POST)
    public String registerCustomer(Map<String, Object> map, @ModelAttribute("user") User user, @Valid User signUpValid, BindingResult result) {
        if (result.hasErrors()) {
            map.put("countryList", countryService.listCountry());
            map.put("stateList", stateService.listState());
            map.put("search", new Search());
            return "signUp";
        } else {
            try {
                user.setAuthority("CUSTOMER");
                user.setEnabled(true);
                userService.saveUser(user);
                mailService.sendMail(emailFrom,
                user.getEmailAddress(),
                "Sharanam & Vaishali's Online Bookshop: Registration mail", 
                "<table width='100%' border='0' align='center' cellpadding='15' cellspacing='0' "
                        + "style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12pt; color:#5a5a5a;'>"
                        + "<tr><td align='left'>Dear " + user.getFirstName() + ",</td></tr><tr>"
                        + "<td align='left'>Your login details are:<br/><br/>"
                        + "Username: " + user.getUserName() + "<br />"
                        + "Password: " + user.getPassword() + "<br /><br/>"
                        + "<p>Thank you for using  this site.<br />"
                        + "</p><br/><br/><p>Regards,<br />Sharanam & Vaishali's Online Bookshop<br />"
                        + "</p><p><br /><br />THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY. </p>"
                        + "</td></tr></table>");
                return "redirect:/signUpThankYou";
            } catch (ConstraintViolationException exp) {
                map.put("dbError", exp.getMessage());
                return "signUp";
            }
        }
    }

    @RequestMapping("/signUpThankYou")
    public String showSignUpThankYou(Map<String, Object> map) {
        map.put("search", new Search());
        return "signUpThankYou";
    }
}
