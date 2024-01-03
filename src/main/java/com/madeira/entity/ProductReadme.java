package com.madeira.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "Product_Text")
@Table(
    name = "Product_Readme"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductReadme {
    
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(
        name = "doc_id", 
        updatable = false
    )
    private UUID id;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(
        name = "readme_text",
        nullable = false
    )
    private String readmeText;

    @Column(
        name = "last_updated",
        nullable = false,
        columnDefinition = "DATE"
    )
    private LocalDate lastUpdated; 

    @OneToOne (
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @JoinColumn(
        name = "product_id",
        referencedColumnName = "product_id"
    )
    private Product product;

}
