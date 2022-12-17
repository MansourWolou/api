package com.example.demo.Content;

import com.example.demo.user.UserModel;
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
    private long id;
    private String type;
    private String description;
    private String contentPath;
    @OneToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id")
    private UserModel user;
}
