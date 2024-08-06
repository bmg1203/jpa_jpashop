package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    //얘의 주인은 오더스 테이블의 member_id이다
    @JsonIgnore //멤버를 부를 떄 주문은 뺄 수 있음
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
