package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryOld memberRepository;

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 검증 로직
    //멤버 수를 세어서 하는 것이 좀 더 최적화
    private void validateDuplicateMember(Member member) {
        //EXCEPTI
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 한명 조회 id로
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        //db에서 영속성 컨텍스트를 찾음
        Member member = memberRepository.findOne(id);
        //영속상태인 것을 변경해주면, 얘가 종료되면서 스프링 AOP가 동작한다.
        //트랜잭셔널 어노테이션에 의해서 트랜잭션 관련 AOP가 끝나는 시점에 트랜잭션 커밋이 일어남
        //그러면 JPA가 플러쉬하고 영속성 컨텍스트 커밋을 하여 DB가 커밋됨
        member.setName(name);
    }
}
