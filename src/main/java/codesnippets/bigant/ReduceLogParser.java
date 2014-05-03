package codesnippets.bigant;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.telekinesis.commonclasses.collection.KeyToValueList;
import org.telekinesis.commonclasses.io.LocalLineFileReader;

public class ReduceLogParser
{
    public KeyToValueList<String, Timestamp> parse(String path) throws IOException{
	TaskLineParser parser = new TaskLineParser();
	LocalLineFileReader reader = new LocalLineFileReader();
	reader.read(path, parser, 0);
	List<TaskRecord> records = parser.getRecords();
	final KeyToValueList<String, Timestamp> timeSpots = new KeyToValueList<String, Timestamp>();
	TargetTaskLogExtractor extractor = new TargetTaskLogExtractor(
		new TaskRecordFilter()
		{
		    
		    @Override
		    public void doSomething(TaskRecord record)
		    {
			timeSpots.put("reduce() start time", record.getTime());
		    }
		    
		    @Override
		    public boolean accept(TaskRecord record)
		    {
			return record.getSourceClass().equals("org.apache.hadoop.mapred.MapTask")
				&& record.getInformation().contains("record buffer");
		    }
		},
		new TaskRecordFilter()
		{
		    
		    @Override
		    public void doSomething(TaskRecord record)
		    {
			timeSpots.put("reduce() end time", record.getTime());
		    }
		    
		    @Override
		    public boolean accept(TaskRecord record)
		    {
			return record.getSourceClass().equals("org.apache.hadoop.mapred.MapTask")
				&& record.getInformation().contains("Finished spill");
		    }
		}
	);
	extractor.extract(records);
	timeSpots.put("reduce task start time", records.get(0).getTime());
	timeSpots.put("reduce task end time", records.get(records.size() - 1).getTime());
	return timeSpots;
    }
}
