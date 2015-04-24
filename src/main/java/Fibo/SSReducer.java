package Fibo;

import api.MyContext;
import api.MyReducer;
import impl.StringWritable;
import java.io.IOException;
import java.util.Iterator;


/**
 *  Created by Vishal
 */
public class SSReducer implements MyReducer<StringWritable, StringWritable> {

	@Override
	public void reduce(StringWritable key, Iterator<StringWritable> values, MyContext myContext) {
		StringBuilder sb = new StringBuilder();
		while(values.hasNext()){
			sb.append(values.next().getString()+"\t");
		}

		try {
			myContext.write(key, new StringWritable(new String(sb)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}