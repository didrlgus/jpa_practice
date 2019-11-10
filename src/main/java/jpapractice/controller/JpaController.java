package jpapractice.controller;

import jpapractice.model.Member;
import jpapractice.model.Team;
import jpapractice.repository.MemberRepository;
import jpapractice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JpaController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/")
    public ResponseEntity<?> init() {

        /*memberRepository.save(Member.builder().name("양기현").team().build());*/

        Member member = Member.builder().name("양기현").build();
        Member member1 = Member.builder().name("손흥민").build();
        Member member2 = Member.builder().name("해리케인").build();

        Team team = Team.builder().name("토트넘").build();
        Team team2 = Team.builder().name("리버풀").build();

        // member가 관계의 주인.
        // member쪽에서 team을 설정하면 관계가 성립됨
        // member쪽 (주인쪽) 설정 안해주면 매핑되지 않음
        member.setTeam(team);
        member1.setTeam(team);
        member2.setTeam(team);

        List<Member> members = new ArrayList<>();
        team.setMemberList(members);

        team.getMemberList().add(member1);
        team.getMemberList().add(member2);

        teamRepository.save(team);
        memberRepository.save(member);
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> memberList = memberRepository.findAll();

        for(Member m : memberList) {
            log.info("name : {}, team_name : {}", m.getName(), m.getTeam().getName());
        }

        List<Team> teamList = teamRepository.findAll();

        for(Team t : teamList)
            log.info("team_name : {}, member : {}", t.getName(), t.getMemberList());

        return ResponseEntity.ok("good");
    }
}
