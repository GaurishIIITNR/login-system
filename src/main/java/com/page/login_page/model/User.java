package com.page.login_page.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @Column(unique = true, nullable = false)
     private String userName;
     @Column(nullable = false)
     private String name;
     @Column(unique = true, nullable = false)
     private String email;
     @Column(nullable = false)
     private  String password;
     private String randomToken;
     @Column(nullable = false)
     private String role_type;
}
