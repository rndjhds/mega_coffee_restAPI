package com.megacoffee_jpa_restapi.entity.member;

import com.megacoffee_jpa_restapi.entity.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member implements Persistable<String> {

    @Id
    @Column(name = "member_id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Enumerated(EnumType.STRING)
    private PermitStatus permitStatus;

    @Column(name = "delete_yn")
    private String deleteYN;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return createDate == null;
    }

    public void changeMemberInfo(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void changeMemberStatus(MemberType memberType, PermitStatus permitStatus, String deleteYN) {
        this.memberType = MemberType.BUYER;
        this.permitStatus = PermitStatus.DENIED;
        this.deleteYN = deleteYN;
    }
}
