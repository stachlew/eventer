package pl.wat.db.domain.user;

import pl.wat.db.domain.user.Authority;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EVE_Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_USER_SEQ")
    @SequenceGenerator(sequenceName = "EVE_USER_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_USER_SEQ")
    private int id;

    @Column(length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String firstname;

    @Column(length = 50)
    @NotNull
    @Size(min = 3, max = 50)
    private String lastname;

    @Column(length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @Column(length = 50)
    @Size(min = 4, max = 50)
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @NotNull
    private boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastpassres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "EVE_USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "ID")},
            uniqueConstraints = {@UniqueConstraint(
                    columnNames = {"user_id", "authority_id"})})
    private List<Authority> authorities;

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

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastpassres() {
        return lastpassres;
    }

    public void setLastpassres(Date lastpassres) {
        this.lastpassres = lastpassres;
    }
}