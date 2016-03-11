package com.donateknowledge;

import com.donateknowledge.model.Author;
import com.donateknowledge.service.AuthorService;
import com.donateknowledge.service.CommonService;
import com.donateknowledge.service.CountryService;
import com.donateknowledge.service.StateService;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AuthorController {
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private AuthorService authorService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private StateService stateService;

    @RequestMapping("/manageAuthors")
    public String showManageAuthors(Map<String, Object> map) {
        map.put("author", new Author());
        map.put("authorList", authorService.listAuthor());
        map.put("countryList", countryService.listCountry());
        map.put("stateList", stateService.listState());
        return "manageAuthors";
    }
    
    @RequestMapping("/downloadAuthorPhotograph/{authorNo}")
    public String downloadAuthorPhotograph(@PathVariable("authorNo") Integer authorNo, HttpServletResponse response) {
        Author author = authorService.getAuthorById(authorNo);
        try {
            if (author.getPhotograph()!=null) {
                response.setHeader("Content-Disposition", "inline;filename=\"" + author.getFirstName() + "\"");
                OutputStream out = response.getOutputStream();
                response.setContentType("image/gif");
                IOUtils.copy(author.getPhotograph().getBinaryStream(), out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return null;
    }

    @RequestMapping(value = "/saveAuthor", method = RequestMethod.POST)
    public String saveAuthor(Map<String, Object> map, @ModelAttribute("author") Author author, @Valid Author authorValid, BindingResult result, @RequestParam("file") MultipartFile file) throws IOException {
        if (result.hasErrors()) {
            map.put("countryList", countryService.listCountry());
            map.put("stateList", stateService.listState());
            return "manageAuthors";
        } else {
            if(!file.isEmpty()) {
                author.setPhotograph(commonService.getBlob(file.getBytes())); 
            }
            else {
                if (author.getAuthorNo() != null) {
                    author.setPhotograph(authorService.getAuthorById(author.getAuthorNo()).getPhotograph());
                }
            }
            try {
                authorService.saveAuthor(author);
                return "redirect:/admin/manageAuthors";
            } catch (ConstraintViolationException exp) {
                map.put("dbError", exp.getMessage());
                return "manageAuthors";
            }
        }
    }

    @RequestMapping("/deleteAuthor/{authorNo}")
    public String deleteAuthor(Map<String, Object> map, @PathVariable("authorNo") Integer authorNo) {
        try {
            authorService.removeAuthor(authorNo);
            return "redirect:/admin/manageAuthors";
        } catch (DataIntegrityViolationException exp) {
            map.put("dbError", "Cannot delete a parent row.");
            map.put("author", new Author());
            return "manageAuthors";
        }
    }
    
    @RequestMapping("/editAuthor/{authorNo}")
    public String editAuthor(Map<String, Object> map, @PathVariable("authorNo") Integer authorNo) {
        map.put("author", authorService.getAuthorById(authorNo));
        map.put("countryList", countryService.listCountry());
        map.put("stateList", stateService.listState());
        return "manageAuthors";
    }
}
