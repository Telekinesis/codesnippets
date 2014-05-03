package codesnippets.bigant;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.telekinesis.commonclasses.collection.KeyToValueList;

public class TestParseLine
{
    private static final String line = "2014-04-30 07:46:58,256 INFO org.apache.hadoop.mapred.MapTask: io.sort.mb = 100";
    private static final String mapLogPath = "src/main/resources/bigant/mapLog";
    private static final String reduceLogPath = "src/main/resources/bigant/reduceLog";

    @Test
    public void testRegex()
    {
	String regex = "(\\S+\\s+\\S+)(\\s+)(\\S+)(\\s+)(\\S+)(\\:\\s*)(.*)";
	Pattern pattern = Pattern.compile(regex);
	Matcher m = pattern.matcher(line);
	if (m.find())
	{
	    for (int i = 0; i <= m.groupCount(); i++)
	    {
		System.out.println(m.group(i));
	    }
	}
    }
    
    @Test
    public void testParseTime(){
	System.out.println(Timestamp.valueOf("2014-04-30 07:46:58,256".replaceAll("\\,", ".")));
    }
    
    @Test
    public void testParseFile() throws IOException{
	MapLogParser mapParser = new MapLogParser();
	KeyToValueList<String, Timestamp> mapTaskTimeSpots = mapParser.parse(mapLogPath);
	ReduceLogParser reduceParser = new ReduceLogParser();
	KeyToValueList<String, Timestamp> reduceTaskTimeSpots = reduceParser.parse(reduceLogPath);
	System.out.println(mapTaskTimeSpots.toString());
	System.out.println(reduceTaskTimeSpots.toString());
    }
}
