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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class UserController {
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

    @RequestMapping("/manageUsers")
    public String showManageUsers(Map<String, Object> map) {
        map.put("user", new User());
        map.put("userList", userService.listUser());
        map.put("countryList", countryService.listCountry());
        map.put("stateList", stateService.listState());
        return "manageUsers";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(Map<String, Object> map, @ModelAttribute("user") User user, @Valid User userValid, BindingResult result) {
        if (result.hasErrors()) {
            map.put("countryList", countryService.listCountry());
            map.put("stateList", stateService.listState());
            return "manageUsers";
        } else {
            try {
                userService.saveUser(user);
                return "redirect:/admin/manageUsers";
            } catch (ConstraintViolationException exp) {
                map.put("dbError", exp.getMessage());
                return "manageUsers";
            }
        }
    }

    @RequestMapping("/deleteUser/{userNo}")
    public String deleteUser(Map<String, Object> map, @PathVariable("userNo") Integer userNo) {
        User user = userService.getUserById(userNo);
        if(!user.getUserName().equals("admin")) {
            userService.removeUser(userNo);
            return "redirect:/admin/manageUsers";
        }
        else {
            map.put("dbError", "You cannot delete the admin user.");
            map.put("user", new User());
            map.put("userList", userService.listUser());
            map.put("countryList", countryService.listCountry());
            map.put("stateList", stateService.listState());
            return "manageUsers";           
        }
    }
    
    @RequestMapping("/editUser/{userNo}")
    public String editUser(Map<String, Object> map, @PathVariable("userNo") Integer userNo) {
        map.put("user", userService.getUserById(userNo));
        map.put("countryList", countryService.listCountry());
        map.put("stateList", stateService.listState());
        return "manageUsers";
    }

    @RequestMapping("/forgotPassword")
    public String showForgotPassword(Map<String, Object> map) {
        Search search = new Search();
        map.put("search", search);
        return "forgotPassword";
    }

    @RequestMapping("/retrievePassword/{userName}")
    public String retrievePassword(Map<String, Object> map, @PathVariable("userName") String userName) {
        User user = userService.getUserByUsername(userName);
        String message;
        if (user != null) {
            map.put("userDetails", user);
            mailService.sendMail(emailFrom,
            user.getEmailAddress(),
            "Sharanam & Vaishali's Online Bookshop: Forgot Password mail", 
            "<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' "
                    + "style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12pt; color:#5a5a5a;'>"
                    + "<tr><td align='left'><p>Dear " + user.getFirstName() + ",</p></td></tr><tr>"
                    + "<td align='left'><p>As requested, please find your login details below:</p><br/>"
                    + "<br/><p>Username: " + user.getUserName() + "<br />Password: " + user.getPassword() + 
                    "<br /></p><br/><p>Thank you for using  this site.<br /></p><br/><br/><p>"
                    + "Regards,<br />Sharanam & Vaishali's Online Bookshop<br /></p><p><br /><br />"
                    + "THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY. </p></td></tr></table>");
            message = "Password sent to " + user.getEmailAddress();
        } else {
            message = "Invalid Username. Please try again";
        }
        map.put("message", message);
        Search search = new Search();
        map.put("search", search);
        return "forgotPassword";
    }
}
