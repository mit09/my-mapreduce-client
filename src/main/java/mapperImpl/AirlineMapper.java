package mapperImpl;

import api.MyContext;
import api.MyMapperAPI;
import impl.FloatWritable;
import impl.StringWritable;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mit on 4/2/15.
 */
public class AirlineMapper implements MyMapperAPI {
    
    private final static Logger LOGGER = Logger.getLogger(AirlineMapper.class.getName());

    @Override
    public void map(String line, MyContext myContext) {
        
        int keyIndex = 3;
        int valueIndex = 4;
        
        String[] lineSplit = line.split("\t");
        String key = lineSplit[keyIndex];
        
        
        try {
            Float value = Float.parseFloat(lineSplit[valueIndex]);
            myContext.write(new StringWritable(key), new FloatWritable(value));
        }catch (NumberFormatException e){
            LOGGER.log(Level.WARNING, "NumberFormatException encountered while writing the key:" + key + " value:" + lineSplit[valueIndex] + " for line: " + line);
        }
        catch (IOException e) {
            LOGGER.log(Level.WARNING, "IOException encountered while writing the key:" + key + " value:" + lineSplit[valueIndex] + " for line: " + line);
        }
    }

}
