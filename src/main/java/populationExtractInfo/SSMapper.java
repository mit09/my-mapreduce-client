package populationExtractInfo;

import java.io.IOException;

import api.MyContext;
import api.MyMapper;
import impl.LongWritable;
import impl.StringWritable;

/**
 * Created by Vishal
 */
public class SSMapper implements
        MyMapper<LongWritable, StringWritable> {

    /**
     * Run map job
     * @param key
     * @param value
     * @param myContext
     */
    @Override
    public void map(LongWritable key, StringWritable value, MyContext myContext) {
        String line = value.getString();
        String[] info = line.split("\t");
        try {
        String nfo = info[0] + "\t" + info[1] + "\t" + info[6] + "\t" + info[7];
            myContext.write(new StringWritable(info[2]), new StringWritable(nfo));
        } catch (IOException e) {
            e.printStackTrace();
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}