package BaseUrlExpansion;
import api.JobConf;

public class SSJobConf extends JobConf{

	@Override
	public void initialize() {
		JobConf.setMapKeyInputClassName("impl.LongWritable");
		JobConf.setMapValueInputClassName("impl.StringWritable");
		JobConf.setMapKeyOutputClassName("impl.StringWritable");
		JobConf.setMapValueOutputClassName("impl.StringWritable");
		JobConf.setMapperClassName("BaseUrlExpansion.SSMapper");
		JobConf.setReducerClassName("BaseUrlExpansion.SSReducer");
//		JobConf.setNumReducers(2);
	}

}