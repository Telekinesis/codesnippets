package codesnippets.bigant;

import java.util.List;

public class TargetTaskLogExtractor
{
    private TaskRecordFilter[] filters;

    public TargetTaskLogExtractor(TaskRecordFilter... filters)
    {
	this.filters = filters;
    }
    
    public void extract(List<TaskRecord> records){
	for (TaskRecord attemptRecord : records)
        {
	    for (TaskRecordFilter filter : filters)
            {
	        if(filter.accept(attemptRecord)){
	            filter.doSomething(attemptRecord);
	        }
            }
        }
    }
}
