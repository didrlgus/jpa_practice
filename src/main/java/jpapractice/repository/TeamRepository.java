package jpapractice.repository;

import jpapractice.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom{
    @Query("SELECT t FROM Team t join fetch t.memberSet order by t.id asc ")
    Set<Team> findAllFetch();
}
