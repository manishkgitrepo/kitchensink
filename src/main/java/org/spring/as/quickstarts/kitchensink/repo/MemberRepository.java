package org.spring.as.quickstarts.kitchensink.repo;

import org.spring.as.quickstarts.kitchensink.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    List<Member> findAllByOrderByNameAsc();
}
