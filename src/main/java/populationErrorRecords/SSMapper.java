package populationErrorRecords;
import java.io.IOException;

import api.MyContext;
import api.MyMapper;
import impl.LongWritable;
import impl.StringWritable;

/**
 *  Created by Vishal
 */
public class SSMapper implements MyMapper<LongWritable, StringWritable>
{
	/**
	 * Run map
	 * @param key
	 * @param value
	 * @param myContext
	 */
	@Override
	public void map(LongWritable key, StringWritable value, MyContext myContext){
		String line = value.getString();
		String[] info = line.split("\t");
		{
			try {
				myContext.write(new StringWritable(info[2]), value);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ArrayIndexOutOfBoundsException e){
				e.printStackTrace();
			}
		}
	}
}