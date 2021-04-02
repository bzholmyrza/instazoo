package kz.bzholmyrza.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import kz.bzholmyrza.entity.enums.ERole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data // анотация ламбока, создает геттеры и сеттеры для данного класса
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "text")
    private String bio;

    @Column(length = 3000)
    private String password;

    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();

    @OneToMany(mappedBy = "account", orphanRemoval = true,
            cascade = CascadeType.ALL, //если удалим пользователя, все его посты удаляться с бд
            fetch = FetchType.LAZY)//нам не нужны все его посты
    private List<Post> posts = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public Account(){}

    @PrePersist //будет задавать значение createdDate прямо до того как мы сделаем новую запись в бд
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
