package mapperImpl;

import api.MyContext;
import api.MyMapper;
import impl.FloatWritable;
import impl.LongWritable;
import impl.StringWritable;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Srikar on 4/2/15.
 */
public class AirlineMapper implements MyMapper<LongWritable, StringWritable> {
    
    private final static Logger LOGGER = Logger.getLogger(AirlineMapper.class.getName());

    @Override
    public void map(LongWritable key, StringWritable value, MyContext myContext) {
        int keyIndex = 3;
        int valueIndex = 4;

        String[] lineSplit = value.getString().split("\t");

        try {
            String opKey = lineSplit[keyIndex];

            Float opValue = Float.parseFloat(lineSplit[valueIndex]);
            myContext.write(new StringWritable(opKey), new FloatWritable(opValue));
        }catch (NumberFormatException e){
            LOGGER.log(Level.WARNING, "NumberFormatException encountered while writing for line: " + value.getString());
        }
        catch (IOException e) {
            LOGGER.log(Level.WARNING, "IOException encountered while writing for line: " + value.getString());
        }catch (ArrayIndexOutOfBoundsException e){
            LOGGER.log(Level.WARNING, "ArrayIndexOutOfBoundsException encountered while writing for line: " + value.getString());
        }
    }
}
