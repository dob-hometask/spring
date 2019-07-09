package dev.ione.dob.service;

import dev.ione.dob.models.Member;
import dev.ione.dob.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Optional<Member> getById(UUID id) {
        log.info("IN MemberRepository getById {}",id);
        return memberRepository.findById(id);
    }

    @Override
    public void save(Member member) {
        log.info("IN MemberRepository save {}",member.toString());
        memberRepository.save(member);
    }

    @Override
    public void update(Member member) {
        log.info("IN MemberRepository update {}",member.toString());
        Member findMember = memberRepository.findByUsernameContainingIgnoreCase(member.getUsername())
                .stream()
                .findFirst()
                .orElseGet(Member::new);
        log.info("Found {} fo update", findMember.toString());
        findMember.setUsername(member.getUsername());
        findMember.setDateOfBirth(member.getDateOfBirth());
        memberRepository.save(findMember);
    }

    @Override
    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> getByName(String username) {
        return memberRepository.findByUsernameContainingIgnoreCase(username);
    }

    @Override
    public long getDateDifferInDays(String dob, Calendar today) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try
        {
            dt = sdf.parse(dob);
        }
        catch (final java.text.ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final Calendar BCal = Calendar.getInstance();
        if (dt != null) {
            BCal.setTime(dt);
        }

        // Take your DOB Month and compare it to current month
        final int BMonth = BCal.get(Calendar.MONTH);
        final int CMonth = today.get(Calendar.MONTH);
        final int BDay = BCal.get(Calendar.DAY_OF_MONTH);
        final int CDay = today.get(Calendar.DAY_OF_MONTH);
        BCal.set(Calendar.YEAR, today.get(Calendar.YEAR));

        if(BMonth < CMonth)
        {
            BCal.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
        } else if (BMonth == CMonth) {
            if(BDay < CDay) {
                BCal.set(Calendar.YEAR, today.get(Calendar.YEAR) + 1);
            }
        }

        // Result in millis
        final long millis = BCal.getTimeInMillis() - today.getTimeInMillis();

        return millis / 86400000;
    }
}
