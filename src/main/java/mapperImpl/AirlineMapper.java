package mapperImpl;

import mapper.MyContext;
import mapper.MyMapperAPI;

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
        String[] lineSplit = line.split("\t");
        String key = lineSplit[3];
        String value = lineSplit[4];
        try {
            myContext.write(key, value);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "IOException encountered while writing the key:" + key + " value:" + value + " for line: " + line);
        }
    }

}
