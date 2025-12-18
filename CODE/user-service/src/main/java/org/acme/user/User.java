package org.acme.user;

import java.util.Objects;

public class User {
    private String id;
    private String username;
    private String email;
    private String address;

    public User() {
    }

    public User(String id, String username, String email, String address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, address);
    }

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + ''' +
               ", username='" + username + ''' +
               ", email='" + email + ''' +
               ", address='" + address + ''' +
               '}';
    }
}
