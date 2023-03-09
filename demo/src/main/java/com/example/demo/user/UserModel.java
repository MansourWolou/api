package com.example.demo.user;
import com.example.demo.content.ContentModel;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
@Table(name = "user_table")
public class UserModel {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "user_id",
            updatable = false
    )
    private Long id;
    private String name;
    private String email;

    //@OneToMany(targetEntity = ContentModel.class,cascade = CascadeType.ALL)
    //@JoinColumn(name = "userTableId" , referencedColumnName = "user_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userTableId",referencedColumnName = "user_id" )
    private List<ContentModel> content;

    public UserModel(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserModel() {

    }
}
