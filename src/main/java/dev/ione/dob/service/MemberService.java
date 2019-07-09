package dev.ione.dob.service;

import dev.ione.dob.models.Member;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberService {

    Optional<Member> getById(UUID id);

    void save(Member member);

    void update(Member member);

    List<Member> getAll();

    List<Member> getByName(String username);

    long getDateDifferInDays(String dob, Calendar today);

}
