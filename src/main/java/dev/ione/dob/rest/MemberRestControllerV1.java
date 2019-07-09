package dev.ione.dob.rest;

import dev.ione.dob.models.Member;
import dev.ione.dob.models.MemberResponse;
import dev.ione.dob.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/hello")
public class MemberRestControllerV1 {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "{username}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MemberResponse> getMember(@PathVariable("username") String userName) {
        if(userName == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Member> members = this.memberService.getByName(userName);
        if (members.size() == 0 ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Member member = members.stream().findFirst().get();
        MemberResponse memberResponse = new MemberResponse();
        final Calendar today = Calendar.getInstance();
        long daystoBirthday = memberService.getDateDifferInDays(member.getDateOfBirth(), today);

        if (daystoBirthday == 0) {
            memberResponse.message = String.format("Hello, %s! Happy birthday!", member.getUsername());
        } else {
            memberResponse.message = String.format("Hello, %s! Your birthday (%s) is in %s day(s)",
                    member.getUsername(), member.getDateOfBirth(), daystoBirthday);
        }
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping("{username}")
    public ResponseEntity<String> create(@PathVariable String username, @RequestBody Member member) {
        this.memberService.save(member);
        return ResponseEntity.ok("Created");

    }

    @PutMapping("{username}")
    public ResponseEntity<String> update(@PathVariable String username, @RequestBody Member member) {
        this.memberService.update(member);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);

    }

}
