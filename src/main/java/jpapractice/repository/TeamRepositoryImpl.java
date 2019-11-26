package jpapractice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpapractice.domain.Team;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static jpapractice.QTeam.team;
import static jpapractice.QMember.member;

@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Team> getAllTeamList() {
        return queryFactory.selectFrom(team)
                .innerJoin(team.memberSet, member)
                .orderBy(team.id.asc())
                .fetchJoin()
                .fetch();
    }
}
