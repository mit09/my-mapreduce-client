package ProjFibo;
import api.JobConf;

public class SSJobConf extends JobConf {

	@Override
	public void initialize(){
		JobConf.setMapKeyInputClassName("impl.LongWritable");
		JobConf.setMapValueInputClassName("impl.StringWritable");
		JobConf.setMapKeyOutputClassName("impl.StringWritable");
		JobConf.setMapValueOutputClassName("impl.StringWritable");
        /*
        JobConf.setReduceKeyOutputClassName("impl.StringWritable");
        JobConf.setReduceValueOutputClassName("impl.FloatWritable");
        */
		JobConf.setMapperClassName("ProjFibo.SSMapper");
		JobConf.setReducerClassName("ProjFibo.SSReducer");

	}

}