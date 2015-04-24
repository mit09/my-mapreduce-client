package Fibo;

import api.JobConf;

/**
 * Created by Vishal on 4/20/15.
 */
public class SSJobConf extends JobConf{
    /**
     * Initialize JobConf
     */
    @Override
    public void initialize() {
        JobConf.setMapKeyInputClassName("impl.LongWritable");
        JobConf.setMapValueInputClassName("impl.StringWritable");
        JobConf.setMapKeyOutputClassName("impl.StringWritable");
        JobConf.setMapValueOutputClassName("impl.StringWritable");
        JobConf.setMapperClassName("Fibo.SSMapper");
        JobConf.setReducerClassName("Fibo.SSReducer");


    }
}
