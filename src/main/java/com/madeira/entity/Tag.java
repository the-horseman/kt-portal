package com.madeira.entity;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Tag")
@Table(
    name = "Tag",
    uniqueConstraints = {
        @UniqueConstraint(name = "tag_name_unique", columnNames = "name")
    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Tag {
    
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(
        name = "tag_id",
        updatable = false
    )
    private UUID tagId;  

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
    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @JoinTable(
        name = "tag_product_map",
        joinColumns = @JoinColumn(
            name = "tag_id",
            referencedColumnName = "tag_id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "product_id",
            referencedColumnName = "product_id"
        )
    )
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "tags"
    )
    private List<Video> videos = new ArrayList<>();

    public Tag (String name, String description) {
        this.name = name;
        this.description = description;
    }

}
