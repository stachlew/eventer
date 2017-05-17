package pl.wat.logic.user._model;

import java.util.Date;

public class UserAdministrationSearchResult {
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private boolean enabled;
    private Date lastpassres;

    public UserAdministrationSearchResult() {
    }

    public UserAdministrationSearchResult(int id, String username, String firstname, String lastname, String email, String phone, boolean enabled, Date lastpassres) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.enabled = enabled;
        this.lastpassres = lastpassres;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastpassres() {
        return lastpassres;
    }

    public void setLastpassres(Date lastpassres) {
        this.lastpassres = lastpassres;
    }
}
