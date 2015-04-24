package ProjFibo;
import api.MyContext;
import api.MyMapper;
import impl.LongWritable;
import impl.StringWritable;

import java.io.IOException;

/**
 *  Created by Vishal
 */

public class SSMapper implements MyMapper<LongWritable, StringWritable> {
	/**
	 * Run map job
	 * @param key
	 * @param value
	 * @param context
	 */
	@Override
	public void map(LongWritable key, StringWritable value, MyContext context)
			{
		String line = value.getString();

				try {
					context.write(new StringWritable(""+key.getString()), new StringWritable(line));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
}