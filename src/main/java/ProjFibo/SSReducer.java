package ProjFibo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class SSReducer extends
		Reducer<Text, Text, Text, Text> {
	

	 public static int fibonacciRecusion(int number, ArrayList<String> al){
		 	al.add(number+" ");
	        if(number == 1 || number == 2){
	            return 1;
	        }
	 
	        return fibonacciRecusion(number-1,al) + fibonacciRecusion(number -2,al);
	    }
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> al = new ArrayList<String>();
		int result = 0;
		for (Text value : values) {
			al = new ArrayList<String>();
			if(Integer.parseInt(value.toString()) > 50){
				continue;
			}
			result = fibonacciRecusion(Integer.parseInt(value.toString()),al);
			Iterator<String> it = al.iterator();			
			while(it.hasNext()){
				sb.append(it.next()+" ");
			}
			sb.append(result+ " "+((value.toString())+ "\n"));
		}
		context.write(key, new Text(new String(sb)));		
	}
}