package ProjFibo;
import api.MyContext;
import api.MyMapper;
import impl.LongWritable;
import impl.StringWritable;

import java.io.IOException;


public class SSMapper implements MyMapper<LongWritable, StringWritable> {

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