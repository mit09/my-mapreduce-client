package BaseUrlExpansion;
import java.io.IOException;
import java.util.Iterator;

import api.MyContext;
import api.MyReducer;
import impl.StringWritable;


/**
 *  Created by Vishal
 */
public class SSReducer implements
		MyReducer<StringWritable, StringWritable> {

	@Override
	public void reduce(StringWritable key, Iterator<StringWritable> values, MyContext myContext) {

		StringBuilder sb = new StringBuilder();

		while(values.hasNext()){
			String value = values.next().getString();
			sb.append(value+ " ");
		}
		try {
			myContext.write(key, new StringWritable(new String(sb)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}