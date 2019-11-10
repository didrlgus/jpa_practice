package jpapractice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpapractice.Team;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

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
