package com.page.login_page.Services;

import com.mysql.cj.util.StringUtils;
import com.page.login_page.model.LoginReq;
import com.page.login_page.model.User;
import com.page.login_page.reposetory.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServices {
   @Autowired
   UserRepo userRepo;
   @Autowired
    JavaMailSender mailSender;

   public void CreateUser(User user){
       if(userRepo.findByUserName(user.getUserName()).isPresent()){
           throw new RuntimeException("Username already in use !!");
       }
       if(userRepo.findByEmail(user.getEmail()).isPresent()){
           throw new RuntimeException("Email Already Registered..Please Login !!");
       }
       userRepo.save(user);
   }

   public void LoginByEmail(LoginReq loginReq){
       Optional<User> temp = userRepo.findByEmail(loginReq.getLoginValue());
       if(temp.isEmpty()){
           throw new RuntimeException("Email Not Found...Please SignUp !!");
       }
       User user = temp.get();
       if(!user.getPassword().equals(loginReq.getPassword())){
           throw new RuntimeException("Password did not match...Please try again !!");
       }
   }
   public void LoginByUserName(LoginReq loginReq){
       Optional<User> temp = userRepo.findByUserName(loginReq.getLoginValue());
       if(temp.isEmpty()){
           throw new RuntimeException("Username Not Found.");
       }
       User user = temp.get();
       if(!user.getPassword().equals(loginReq.getPassword())){
           throw new RuntimeException("Password did not match...Please try again !!");
       }
   }

   public void sendMail(String email, String body){
       SimpleMailMessage mail = new SimpleMailMessage();
       mail.setFrom("ojha@gmail.com");
       mail.setTo(email);
       mail.setSubject("Password Reset  link");
       mail.setText(body);
       mailSender.send(mail);
   }

   public void sendURL(String email){
       Optional<User> temp = userRepo.findByEmail(email);
       if(temp.isEmpty()){
          throw new RuntimeException("Email Not Found !!");
       }

       User user = temp.get();
       String tkn = UUID.randomUUID().toString();
       String URL = "http://localhost:8080/reset?userName="+user.getUserName()+"&randomToken="+tkn;
       user.setRandomToken(tkn);
       userRepo.save(user);

       // TODO Email
       String body = "Hii "+user.getName()+" !! \n\n" +"To reset your password,\n Please click the link below.\n"+URL+"\n\nThanks \nGaurish Ojha";
       sendMail(email, body);
   }

   public void resetPass(String userName, String randomToken, String password){
       Optional<User> temp = userRepo.findByUserName(userName);
       if(temp.isEmpty()){
           throw new RuntimeException("User Not Found...Please create another reset link !!");
       }
       User user = temp.get();
       if(StringUtils.isNullOrEmpty(user.getRandomToken()) || !user.getRandomToken().equals(randomToken)){
           throw new RuntimeException("Reset link Already Used...Please create another reset link !!");
       }
       user.setPassword(password);
       user.setRandomToken(null);
       userRepo.save(user);
   }
}
