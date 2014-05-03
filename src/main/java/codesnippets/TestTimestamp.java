package codesnippets;

import java.sql.Timestamp;

import org.junit.Test;

public class TestTimestamp
{
    @Test
    public void test(){

	System.out.println(Timestamp.valueOf("2014-05-01 7:41:15").getTime());
	System.out.println(Timestamp.valueOf("2014-05-01 9:05:51").getTime());
	
	System.out.println(new Timestamp(1398906390 * 1000L));
    }
}
