package com.donateknowledge;

import com.donateknowledge.model.State;
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
public class StateController {
    @Autowired
    private StateService stateService;

    @RequestMapping("/manageStates")
    public String showManageStates(Map<String, Object> map) {
        map.put("state", new State());
        map.put("stateList", stateService.listState());
        return "manageStates";
    }

    @RequestMapping(value = "/saveState", method = RequestMethod.POST)
    public String saveState(Map<String, Object> map, @ModelAttribute("state") State state, @Valid State stateValid, BindingResult result) {
        if (result.hasErrors()) {
            return "manageStates";
        } else {
            try {
                stateService.saveState(state);
                return "redirect:/admin/manageStates";
            } catch (ConstraintViolationException exp) {
                map.put("dbError", exp.getMessage());
                return "manageStates";
            }
        }
    }

    @RequestMapping("/deleteState/{stateNo}")
    public String deleteState(Map<String, Object> map, @PathVariable("stateNo") Integer stateNo) {
        try {
            stateService.removeState(stateNo);
            return "redirect:/admin/manageStates";
        } catch (DataIntegrityViolationException exp) {
            map.put("dbError", "Cannot delete a parent row.");
            map.put("state", new State());
            return "manageStates";
        }
    }
    
    @RequestMapping("/editState/{stateNo}")
    public String editState(Map<String, Object> map, @PathVariable("stateNo") Integer stateNo) {
        map.put("state", stateService.getStateById(stateNo));
        return "manageStates";
    }
}
