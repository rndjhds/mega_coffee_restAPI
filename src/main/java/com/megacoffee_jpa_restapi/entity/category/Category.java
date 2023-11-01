package com.megacoffee_jpa_restapi.entity.category;

import com.megacoffee_jpa_restapi.BaseEntity;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category extends BaseEntity {

    @Id
    @SequenceGenerator(name = "CATEGORY_SEQ_JPA", sequenceName = "CATEGORY_SEQ_JPA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ_JPA")
    @Column(name = "category_id")
    private Long id;

    private String name;
    @Column(name = "delete_yn")
    private String deleteYN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
