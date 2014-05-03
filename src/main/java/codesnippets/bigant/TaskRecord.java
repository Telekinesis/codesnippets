package codesnippets.bigant;

import java.sql.Timestamp;

public class TaskRecord
{
    private final Timestamp time;
    private final String    sourceClass;
    private final String    information;

    public TaskRecord(Timestamp time, String sourceClass, String information)
    {
	this.time = time;
	this.sourceClass = sourceClass;
	this.information = information;
    }

    public Timestamp getTime()
    {
	return time;
    }

    public String getSourceClass()
    {
	return sourceClass;
    }

    public String getInformation()
    {
	return information;
    }

    @Override
    public String toString()
    {
	return "AttemptRecord [time=" + time + ", sourceClass=" + sourceClass
	        + ", information=" + information + "]";
    }

}
