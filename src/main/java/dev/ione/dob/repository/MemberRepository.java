package dev.ione.dob.repository;

import dev.ione.dob.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
    List<Member> findByUsernameContainingIgnoreCase(String username);
}
