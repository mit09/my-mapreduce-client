package ProjFibo;
import api.JobConf;

/**
 *  Created by Vishal
 */
public class SSJobConf extends JobConf {

	/**
	 * Initialize JobConf
	 */
	@Override
	public void initialize(){
		JobConf.setMapKeyInputClassName("impl.LongWritable");
		JobConf.setMapValueInputClassName("impl.StringWritable");
		JobConf.setMapKeyOutputClassName("impl.StringWritable");
		JobConf.setMapValueOutputClassName("impl.StringWritable");s
		JobConf.setMapperClassName("ProjFibo.SSMapper");
		JobConf.setReducerClassName("ProjFibo.SSReducer");

	}

}