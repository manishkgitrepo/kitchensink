package org.spring.as.quickstarts.kitchensink.repo;

import org.spring.as.quickstarts.kitchensink.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MemberRepository extends MongoRepository<Member, Long> {
    Member findByEmail(String email);
    List<Member> findAllByOrderByNameAsc();
}
