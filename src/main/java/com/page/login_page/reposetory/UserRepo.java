package com.page.login_page.reposetory;

import com.page.login_page.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,String> {
     Optional<User> findByUserName(String UserName);
     Optional<User> findByEmail(String Email);
}

