package com.example.emailclient;

import Misc.FileSaver;
import Models.*;
import Models.Immutables.EmailHeaderImmutable;
import Models.Immutables.EmailImmutable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class Controller {


    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json")
    public Map<String, Object> authenticate(@RequestHeader(value="Authorization") String userInfo){
        String authenticatedUserID=SecurityFilter.getInstance().authenticateLogin(userInfo);
        if(authenticatedUserID!="null")
            return SecurityFilter.getInstance().generateBasicInfo(authenticatedUserID);
        Map <String,Object> errorMap=new HashMap<>();
        errorMap.put("authenticated","false");
        return errorMap;
    }

    @RequestMapping(value = "/getMail", method = RequestMethod.GET, produces = "application/json")
    public EmailImmutable getMail(int id) {
        UserSession userSession = new UserSession(1);
        EmailImmutable emI = new EmailImmutable(userSession.getMail(id));
        System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD" + emI.getTitle());
        return emI;
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
    @RequestMapping(value = "/drafts",method = RequestMethod.POST)
    public String drafts(@RequestPart(name ="attachments") MultipartFile[] attachments,
                         @RequestParam(name ="email") String emailJson){
        Map<String, Object> emailMap = null;
        try {
            emailMap = new ObjectMapper().readValue(emailJson, Map.class);
        } catch (JsonProcessingException e) {
            return null;
        }

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
        userSession.draft(emailMap, paths);
        return "Succeeded";
    }


    @RequestMapping(value = "/moveMail",method = RequestMethod.PUT)
    public void moveMail(int []headersId,int currentFolder,int destinationFolder){
        UserSession userSession = new UserSession(1);
        userSession.moveEmail(headersId,currentFolder,destinationFolder);
    }
    @RequestMapping(value = "/copyMail",method = RequestMethod.PUT)
    public void copyMail(int []headersId,int currentFolder,int destinationFolder){
        UserSession userSession = new UserSession(1);
        userSession.copyEmail(headersId,currentFolder,destinationFolder);
    }
    @RequestMapping(value = "/deleteMail",method = RequestMethod.DELETE)
    public void deleteMail(int []headersId,int currentFolder){
        UserSession userSession = new UserSession(1);
        userSession.deleteEmail(headersId,currentFolder);
    }
    @RequestMapping(value = "/addFolder",method = RequestMethod.POST)
    public void addFolder(Map<String,Object> folderMap){
        UserSession userSession = new UserSession(1);
        userSession.addFolder(folderMap);
    }

    @RequestMapping(value = "/deleteFolder",method = RequestMethod.DELETE)
    public void deleteFolder(int folderId){
        UserSession userSession = new UserSession(1);
        userSession.removeFolder(folderId);
    }
    @RequestMapping(value = "/editFolder",method = RequestMethod.PUT)
    public void editFolder(Map<String,Object> folderMap){
        UserSession userSession = new UserSession(1);
        userSession.editFolder(folderMap);
    }


    @RequestMapping(value = "/loadMailHeaders",method = RequestMethod.GET)
    public List<EmailHeaderImmutable> loadMailHeaders(int folderIndex,int page,String criteria){
        UserSession userSession = new UserSession(1);
        return userSession.loadEmailHeaders(folderIndex,page,criteria);
    }

    @RequestMapping(value = "/dumpRetrieve", method = RequestMethod.GET)
    public List<EmailHeaderImmutable> dumpRetrieve() {
        SessionFactory factory = SecurityFilter.getInstance().getSessionFactory();
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        User user = session.find(User.class, 2);
        List<EmailHeader> realList = user.getFolders().get(0).getHeaders();
        trans.commit();
        session.close();
        List<EmailHeaderImmutable> dumpList = new ArrayList<EmailHeaderImmutable>();
        for (EmailHeader eh: realList) {
            dumpList.add(new EmailHeaderImmutable(eh));
        }
        for (EmailHeaderImmutable eh: dumpList) {
            System.out.println(eh.getSenderAddress());
        }
        return dumpList;
    }

}