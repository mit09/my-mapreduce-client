package Fibo;

import api.MyContext;
import api.MyMapper;
import impl.LongWritable;
import impl.StringWritable;

import java.io.IOException;

public class SSMapper implements MyMapper<LongWritable, StringWritable> {

	@Override
	public void map(LongWritable key, StringWritable value, MyContext myContext) {
			String line = value.toString();

			int al = 0;
			try{
				al = Integer.parseInt(line);
				if(al > 40 || al < 2) al = 20;
				al = fibonacciRecusion(al);
			}
			catch(Exception e){
				al = 40;
			}

		try {
			myContext.write(new StringWritable(key.getString()), new StringWritable(new String("" + al + "")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int fibonacciRecusion(int number){
		if(number == 1 || number == 2){
			return 1;
		}

		return fibonacciRecusion(number-1) + fibonacciRecusion(number -2); //tail recursion
	}

}