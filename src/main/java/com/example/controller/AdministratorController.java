package com.example.controller;

import com.example.domain.Administrator;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService adminService;

    @Autowired
    private HttpSession session;

    @GetMapping("login")
    public String loginIndex(){
        return "administrator/login";
    }

    @PostMapping("login")
    public String login(String mailAddress, String password){
        Administrator admin = adminService.findByMailAddressAndPassword(mailAddress, password);
        if (admin == null){
            return "administrator/login";
        }
        session.setAttribute("admin",admin);
        return "redirect:insert";
    }

    @GetMapping("insert")
    public String insertIndex(){
        session.setAttribute("adminList",adminService.findAll());
        return "administrator/insert";
    }

    @PostMapping("insert")
    public String insert(String name,String mailAddress,String password){
        Administrator admin = new Administrator();
        admin.setName(name);
        admin.setMailAddress(mailAddress);
        admin.setPassword(password);
        adminService.save(admin);
        return "redirect:insert";
    }

}
