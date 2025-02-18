package org.spring.as.quickstarts.kitchensink.service;

import org.spring.as.quickstarts.kitchensink.model.Member;
import org.spring.as.quickstarts.kitchensink.repo.IdGenerator;
import org.spring.as.quickstarts.kitchensink.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MemberRegistration {

    private static final Logger log = Logger.getLogger(MemberRegistration.class.getName());

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        member.setId(idGenerator.getNextSequence("member_id"));
        memberRepository.save(member);
        eventPublisher.publishEvent(member);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAllByOrderByNameAsc();
    }
}
