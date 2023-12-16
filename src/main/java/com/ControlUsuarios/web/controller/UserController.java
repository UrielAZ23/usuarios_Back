package com.ControlUsuarios.web.controller;

import com.ControlUsuarios.persitence.entity.UserEntity;
import com.ControlUsuarios.service.UserServer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServer userServer;

    public UserController(UserServer userServer) {
        this.userServer = userServer;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity.ok(userServer.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userServer.getUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user) {
        if (user.getUsername()!=null && !this.userServer.exist(user.getUsername())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            String result = encoder.encode(user.getPassword());
            user.setPassword(result);
            return ResponseEntity.ok(this.userServer.save(user));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/encoded")
    public ResponseEntity<UserEntity> encode(@RequestBody UserEntity user){
        UserEntity user1 = this.userServer.getUserById(user.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        Boolean result= encoder.matches(user.getPassword(),user1.getPassword());
        System.out.println(result);

        return ResponseEntity.ok().build();

    }

    @PutMapping("/update")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity user) {
        if(user.getUsername()!=null && this.userServer.exist(user.getUsername())){
            return ResponseEntity.ok(this.userServer.save(user));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity<Object> statistics(@PathVariable String id){
        UserEntity user = this.userServer.getUserById(id);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("username",user.getUsername());
        data.put("name", user.getName());

        return  ResponseEntity.ok(data);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username){
        if(userServer.exist(username)){
            this.userServer.delete(username);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
