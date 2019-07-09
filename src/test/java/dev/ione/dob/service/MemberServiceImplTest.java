package dev.ione.dob.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceImplTest {

    final Calendar today = Calendar.getInstance();

    @InjectMocks
    private MemberServiceImpl memberService;

    @Before
    public void SetUp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String mockDate = "2019-07-04";
        Date dt = null;
        try
        {
            dt = sdf.parse(mockDate);
        }
        catch (final java.text.ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (dt != null) {
            today.setTime(dt);
        }
    }

    @Test
    public void Should_Return_363() {

        long days = this.memberService.getDateDifferInDays("1984-07-01", today);
        assertEquals("It should be 363", 363, days);
    }

    @Test
    public void Should_Return_0() {

        long days = this.memberService.getDateDifferInDays("1984-07-04", today);
        assertEquals("It should be 0", 0, days);
    }

    @Test
    public void Should_Return_10() {

        long days = this.memberService.getDateDifferInDays("1984-07-14", today);
        assertEquals("It should be 10", 10, days);
    }
}