package com.megacoffee_jpa_restapi.entity.item;

import com.megacoffee_jpa_restapi.BaseEntity;
import com.megacoffee_jpa_restapi.entity.category.Category;
import com.megacoffee_jpa_restapi.entity.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id
    @SequenceGenerator(name = "ITEM_SEQ_JPA",sequenceName = "ITEM_SEQ_JPA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ_JPA")
    @Column(name = "item_id")
    private Long id;
    @Column(name = "price")
    private int price;
    @Column(name = "content")
    private String content;
    @Column(name = "img")
    private String img;
    @Column(name = "title")
    private String title;

    @Column(name = "delete_yn")
    private String deleteYN;
    @Column(name = "option_yn")
    private String optionYN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
