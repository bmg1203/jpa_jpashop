package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    //얘의 주인은 오더스 테이블의 member_id이다
    @OneToMany(mappedBy = "member")
    private List<Order> order = new ArrayList<>();
}
