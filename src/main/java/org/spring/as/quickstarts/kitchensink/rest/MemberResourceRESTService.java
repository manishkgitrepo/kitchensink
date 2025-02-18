package org.spring.as.quickstarts.kitchensink.rest;

import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import org.spring.as.quickstarts.kitchensink.model.Member;
import org.spring.as.quickstarts.kitchensink.repo.MemberRepository;
import org.spring.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/members")
public class MemberResourceRESTService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberRegistration registration;

    @GetMapping
    public List<Member> listAllMembers() {
        return repository.findAllByOrderByNameAsc();
    }

    @Autowired
    private Validator validator;

    @GetMapping("/{id}")
    public ResponseEntity<Member> lookupMemberById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) throws Exception {
        validateMember(member);
        registration.register(member);
        return ResponseEntity.ok(member);
    }

    private void validateMember(Member member) throws ConstraintViolationException, ValidationException {
        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<>(violations));
        }

        // Check the uniqueness of the email address
        if (emailAlreadyExists(member.getEmail())) {
            throw new ValidationException("Unique Email Violation");
        }
    }

    public boolean emailAlreadyExists(String email) {
        Member member = null;
        try {
            member = repository.findByEmail(email);
        } catch (NoResultException e) {
            // ignore
        }
        return member != null;
    }
}
