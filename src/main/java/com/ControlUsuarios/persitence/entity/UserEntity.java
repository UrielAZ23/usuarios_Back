package com.ControlUsuarios.persitence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.encrypt.BytesEncryptor;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(nullable = false,length = 20)
    public String username;
    @Column(nullable = false,length = 200)
    public String name;

    @Column(nullable = false, length = 200)
    private String  password;

    @Column(length = 50)
    private String email;

    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean locked;
    @Column(nullable = false,columnDefinition = "TINYINT")
    private Boolean disabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
