package jpapractice.repository;

import jpapractice.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    @Query("SELECT m FROM Member m join fetch m.team order by m.id asc")
    List<Member> findAllFetch();
}
