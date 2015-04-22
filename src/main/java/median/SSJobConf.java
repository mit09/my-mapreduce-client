package median;
import api.JobConf;

public class SSJobConf extends JobConf{

	@Override
	public void initialize() {
		JobConf.setMapKeyInputClassName("impl.LongWritable");
		JobConf.setMapValueInputClassName("impl.StringWritable");
		JobConf.setMapKeyOutputClassName("impl.StringWritable");
		JobConf.setMapValueOutputClassName("impl.IntWritable");
		JobConf.setMapperClassName("median.SSMapper");
		JobConf.setReducerClassName("median.SSReducer");
//		JobConf.setNumReducers(2);
	}

}