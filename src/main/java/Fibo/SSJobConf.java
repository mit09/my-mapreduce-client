package Fibo;

import api.JobConf;

/**
 * Created by srikar on 4/20/15.
 */
public class SSJobConf extends JobConf{

    @Override
    public void initialize() {
        JobConf.setMapKeyInputClassName("impl.LongWritable");
        JobConf.setMapValueInputClassName("impl.StringWritable");
        JobConf.setMapKeyOutputClassName("impl.StringWritable");
        JobConf.setMapValueOutputClassName("impl.StringWritable");
        /*
        JobConf.setReduceKeyOutputClassName("impl.StringWritable");
        JobConf.setReduceValueOutputClassName("impl.FloatWritable");
        */
        JobConf.setMapperClassName("Fibo.SSMapper");
        JobConf.setReducerClassName("Fibo.SSReducer");


    }
}
