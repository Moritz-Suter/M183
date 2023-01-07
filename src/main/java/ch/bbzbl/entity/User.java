package ch.bbzbl.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedQuery(name = "User.findUserByUsernamePwd", query = "select u from User u where u.username = :username and u.password = :password")
@NamedQuery(name = "User.findUserByName", query = "select u from User u where u.username = :username")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_USERNAMEPWD = "User.findUserByUsernamePwd";
    public static final String FIND_BY_NAME = "User.findUserByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String email;

    private String password;

    private String salt;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String userName, String password,String salt ,Role role) {
        this.username = userName;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            return user.getId() == this.id;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
