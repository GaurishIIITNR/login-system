package com.page.login_page.controller;

import com.page.login_page.model.LoginReq;
import com.page.login_page.Services.UserServices;
import com.page.login_page.model.Response;
import com.page.login_page.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController{
    @Autowired
    UserServices userServices;

    @PostMapping ("/signup")
    public Response SignUp(@RequestBody User user){
        try{
            userServices.CreateUser(user);
            return new Response("User Created Successfully !!", HttpStatus.OK);
        }
        catch (RuntimeException e){
            return  new Response(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public Response Login(@RequestBody LoginReq loginReq){
        try{
            switch(loginReq.getLoginType()){
                case EMAIL -> userServices.LoginByEmail(loginReq);
                case USERNAME -> userServices.LoginByUserName(loginReq);
                default -> throw new RuntimeException("Invalid Login Type");
            }
            String msg = "Successfully Login as " + loginReq.getLoginValue();
            return new Response(msg, HttpStatus.OK);
        }
        catch (Exception e){
             return new Response(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/forgot")
    public Response ForgotPass(@RequestParam String email){
         try{
            userServices.sendURL(email);
            return new Response("Reset Link send on email " + email, HttpStatus.OK);
         }
         catch (Exception e){
             return new Response(e.getMessage(),HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping("/reset")
    public Response reset(@RequestParam String userName, String randomToken, @RequestHeader String password){
        try {
            userServices.resetPass(userName, randomToken, password);
            return new Response("Password Reset Successfully", HttpStatus.OK);
        }
        catch (Exception e){
            return new Response(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
