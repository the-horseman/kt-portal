package com.madeira.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Product")
@Table(
    name = "Product",
    uniqueConstraints = {
        @UniqueConstraint(name = "product_name_unique", columnNames = "name")
    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(
        name = "product_id", 
        updatable = false
    )
    private UUID productId;

    @Column(
        name = "name", 
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    @Column(
        name = "description",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String description; 

    @JsonIgnore
    @OneToOne(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "product"
    )
    private ProductReadme productReadme;

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "products"
    )
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "products"
    )
    private List<Employee> employees = new ArrayList<>();

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
