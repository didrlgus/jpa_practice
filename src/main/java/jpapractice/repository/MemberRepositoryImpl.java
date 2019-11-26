package jpapractice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpapractice.domain.Member;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static jpapractice.QMember.member;
import static jpapractice.QTeam.team;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Member> getAllMemberList() {

        return jpaQueryFactory.selectFrom(member)
                .innerJoin(member.team, team)
                .fetchJoin()
                .orderBy(member.id.asc())
                .fetch();
    }
}
