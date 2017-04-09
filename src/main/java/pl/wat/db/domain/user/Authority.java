package pl.wat.db.domain.user;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "EVE_Authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_AUTH_SEQ")
    @SequenceGenerator(sequenceName = "EVE_AUTH_SEQ", initialValue = 1, allocationSize = 1, name = "EVE_AUTH_SEQ")
    private int id;

    @Column(length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}