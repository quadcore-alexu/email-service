package com.example.emailclient;

import Misc.FileSaver;
import Models.Email;
import Models.SecurityFilter;
import Models.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class Controller {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(@RequestHeader(value="Authorization") String userInfo){
        return  SecurityFilter.getInstance().authenticateLogin(userInfo);
    }

    @RequestMapping(value = "/sendMail", method = RequestMethod.PUT)
    public String sendMail( @RequestPart(name ="attachments") MultipartFile[] attachments,
                            @RequestParam(name ="email") String emailJson,
                            @RequestPart(name ="receivers") String receiversStr) {
        Map<String, Object> emailMap = null;
        try {
            emailMap = new ObjectMapper().readValue(emailJson, Map.class);
        } catch (JsonProcessingException e) {
            return null;
        }
        String[] receivers = receiversStr.split(" ");
        String[] paths = new String[attachments.length];
        try {
            int i = 0;
            for (MultipartFile at: attachments) {
                SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
                String fileName = sdf.format(new Date()) + at.getOriginalFilename();
                String path = FileSaver.saveFile(at, fileName);
                if (path == null) {
                    return null;
                }
                paths[i] = path;
                i++;
            }
        } catch (Exception e) {
            return null;
        }
        UserSession userSession = new UserSession(1);
        userSession.sendEmail(emailMap, receivers, paths);
        return "Succeeded";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }

}
