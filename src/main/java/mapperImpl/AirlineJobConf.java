package mapperImpl;

import api.JobConf;

/**
 * Created by srikar on 4/20/15.
 */
public class AirlineJobConf extends JobConf{

    @Override
    public void initialize() {
        JobConf.setMapKeyInputClassName("impl.LongWritable");
        JobConf.setMapValueInputClassName("impl.StringWritable");
        JobConf.setMapKeyOutputClassName("impl.StringWritable");
        JobConf.setMapValueOutputClassName("impl.FloatWritable");
        JobConf.setReduceKeyOutputClassName("impl.StringWritable");
        JobConf.setReduceValueOutputClassName("impl.FloatWritable");
        JobConf.setMapperClassName("mapperImpl.AirlineMapper");
        JobConf.setReducerClassName("mapperImpl.AirlineReducer");


    }
}
