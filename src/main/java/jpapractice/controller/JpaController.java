package jpapractice.controller;

import jpapractice.domain.Coach;
import jpapractice.domain.Member;
import jpapractice.domain.Team;
import jpapractice.repository.CoachRepository;
import jpapractice.repository.MemberRepository;
import jpapractice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.LinkedHashSet;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JpaController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;

    @GetMapping("/")
    public ResponseEntity<?> init() {

        /*memberRepository.save(Member.builder().name("양기현").team().build());*/

        Member member = Member.builder().name("양기현").build();
        Member member1 = Member.builder().name("손흥민").build();
        Member member2 = Member.builder().name("해리 케인").build();
        Member member3 = Member.builder().name("모하메드 살라").build();
        Member member4 = Member.builder().name("리오넬 메시").build();
        Member member5 = Member.builder().name("루이스 수아레스").build();

        Coach coach = Coach.builder().name("무리뉴").build();
        Coach coach2 = Coach.builder().name("클롭").build();
        Coach coach3 = Coach.builder().name("과르디올라").build();

        Team team = Team.builder().name("토트넘").build();
        Team team2 = Team.builder().name("리버풀").build();
        Team team3 = Team.builder().name("바르셀로나").build();

        // member가 관계의 주인.
        // member쪽에서 team을 설정하면 관계가 성립됨
        // member쪽 (주인쪽) 설정 안해주면 매핑되지 않음
        member.setTeam(team);
        member1.setTeam(team);
        member2.setTeam(team);
        member3.setTeam(team2);
        member4.setTeam(team3);
        member5.setTeam(team3);

        coach.setTeam(team);
        coach2.setTeam(team2);
        coach3.setTeam(team3);

        HashSet<Member> members = new LinkedHashSet<>();
        team.setMemberSet(members);
        team.getMemberSet().add(member1);
        team.getMemberSet().add(member2);

        teamRepository.save(team);
        teamRepository.save(team2);
        teamRepository.save(team3);

        memberRepository.save(member);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);

        coachRepository.save(coach);
        coachRepository.save(coach2);
        coachRepository.save(coach3);

        return ResponseEntity.ok("멤버 추가 완료");
    }
}
