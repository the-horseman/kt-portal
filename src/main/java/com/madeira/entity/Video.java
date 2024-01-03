package com.madeira.entity;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

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
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Video")
@Table(
    name = "Video",
    uniqueConstraints = {
        @UniqueConstraint(name = "video_name_unique", columnNames = "name")
    }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Video {
    
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(
        name = "video_id",
        updatable = false
    )
    private UUID videoId; 

    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    @Column(
        name = "description",
        nullable = true,
        columnDefinition = "TEXT"
    )
    private String description;

    @Column(
        name = "link",
        nullable = true,
        columnDefinition = "TEXT"
    )
    private String link; 

    @Column(
        name = "recorded_date",
        nullable = false,
        columnDefinition = "DATE"
    )
    private LocalDate recordedDate;

    
    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @JoinTable(
        name = "video_tag_map",
        joinColumns = @JoinColumn(
            name = "video_id",
            referencedColumnName = "video_id"
        ),
        inverseJoinColumns = @JoinColumn(
            name = "tag_id",
            referencedColumnName = "tag_id"
        )
    )
    private List<Tag> tags = new ArrayList<>();

    public Video(String name, String description, String link, LocalDate recordedDate) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.recordedDate = recordedDate;
    }
    
}
