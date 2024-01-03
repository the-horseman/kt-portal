package com.madeira.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Employee")
@Table(
    name = "Employee",
    uniqueConstraints = {
        @UniqueConstraint(name = "Employee_email_unique", columnNames = "email")
    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee implements UserDetails {
    
    private static final String ROLE_PREFIX = "ROLE_";

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(
        name = "employee_id",
        updatable = false
    )
    private UUID employeeId;

    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name; 

    @Email
    @Column(
        name = "email",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String email;

    @JsonIgnore
    @Column(
        name = "password",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String password;

    @JsonIgnore
    @Column(
        name = "role",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String role; 

    @Column(
        name = "person_of_contact",
        nullable = false,
        columnDefinition = "BOOLEAN"
    )
    private boolean personOfContact;

    @JsonIgnore
    @Column(
        name = "account_non_expired",
        nullable = false,
        columnDefinition = "BOOLEAN"
    )
    private boolean accountNonExpired = true;

    @JsonIgnore
    @Column(
        name = "account_non_locked",
        nullable = false,
        columnDefinition = "BOOLEAN"
    )
    private boolean accountNonLocked = true;

    @JsonIgnore
    @Column(
        name = "credentials_non_expired",
        nullable = false,
        columnDefinition = "BOOLEAN"
    )
    private boolean credentialsNonExpired = true;

    @JsonIgnore
    @Column(
        name = "enabled",
        nullable = false,
        columnDefinition = "BOOLEAN"
    )
    private boolean enabled = true;

    @JsonIgnore
    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL   
    )
    @JoinTable(
        name = "employee_product_map",
        joinColumns = @JoinColumn(
            name = "employee_id",
            referencedColumnName = "employee_id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "product_id",
            referencedColumnName = "product_id"
        )
    )
    private List<Product> products = new ArrayList<>();

    public Employee(String name, String email, String password, String role, boolean personOfContact) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.personOfContact = personOfContact;
    }
    
    public boolean isPersonOfContact() {
        return this.personOfContact;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ROLE_PREFIX + this.role));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}