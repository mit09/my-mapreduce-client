package populationErrorRecords;
import java.io.IOException;

import api.MyContext;
import api.MyMapper;
import impl.LongWritable;
import impl.StringWritable;

public class SSMapper implements MyMapper<LongWritable, StringWritable>
{
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