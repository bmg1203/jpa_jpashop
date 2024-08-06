package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//<> 안에는 엔티티 타입, pk 타입이 들어감
public interface MemberRepository extends JpaRepository<Member, Long> {
}
