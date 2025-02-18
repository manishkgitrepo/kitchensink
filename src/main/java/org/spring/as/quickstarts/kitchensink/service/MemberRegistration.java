package org.spring.as.quickstarts.kitchensink.service;

import org.spring.as.quickstarts.kitchensink.model.Member;
import org.spring.as.quickstarts.kitchensink.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MemberRegistration {

    private static final Logger log = Logger.getLogger(MemberRegistration.class.getName());

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        memberRepository.save(member);
        eventPublisher.publishEvent(member);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAllByOrderByNameAsc();
    }
}
