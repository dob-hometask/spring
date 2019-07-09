package dev.ione.dob.service;

import dev.ione.dob.models.Member;
import dev.ione.dob.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class DataFillerService {
    private final MemberRepository memberRepository;
    public DataFillerService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostConstruct
    @Transactional
    public void fillData(){
        Member one = new Member("Ivan","1984-07-01");
        Member two = new Member("Dobby","1980-10-01");
        memberRepository.save(one);
        memberRepository.save(two);
    }
}
