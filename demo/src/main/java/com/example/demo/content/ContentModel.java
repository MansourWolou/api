package com.example.demo.content;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "content_table")
public class ContentModel {
    @Id
    @SequenceGenerator(
            name = "content_sequence",
            sequenceName = "content_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "content_sequence"
    )
    @Column(
            name = "content_id",
            updatable = false
    )
    private Long id;
    private String type;
    private String description;
    // NFS
    private String path = "/home/mansour/Téléchargements/moodAgain/demo/src/main/java/com/example/demo/Content/FileUploaded";

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userTableId")
//    private UserModel userTableId;

    public ContentModel(long id, String type, String description, String path) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.path = path;
    }

    public ContentModel(String type, String description, String path) {
        this.type = type;
        this.description = description;
        this.path = path;
    }

    public ContentModel(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public ContentModel() {
    }
}
