package mapperImpl;

import api.MyContext;
import api.MyReducer;
import impl.FloatWritable;
import impl.StringWritable;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by mit on 4/9/15.
 */
public class AirlineReducer implements MyReducer<StringWritable, FloatWritable> {
    @Override
    public void reduce(StringWritable key, Iterator<FloatWritable> value, MyContext myContext) {
        
        float sum =0.0f;
        int counter = 0;
        while(value.hasNext()){
            FloatWritable floatWritable = value.next();
            sum += floatWritable.getNumber();
            counter++;
        }
        float avg = sum/counter;
        
        try {
            myContext.write(key, new FloatWritable(avg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}