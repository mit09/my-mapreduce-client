/*
package populationExtractInfo;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PopContactExtract {
	public static void main(String[] args) throws Exception {
		/ *if (args.length != 2) {
			System.err
					.println("Usage: Median <input path> <output path>");
			System.exit(-1);
		}* /
		String input="/home/vishal/Desktop/testPopulationInfo.txt";
		String op="/home/vishal/Desktop/testPopulationInfo";
		Job job = new Job();
		job.setJarByClass(PopContactExtract.class);
		job.setJobName("Median Find");
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(op));
		job.setMapperClass(SSMapper.class);
		job.setReducerClass(SSReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}

*/
package populationExtractInfo;

import api.JobConf;

/**
 * Created by srikar on 4/20/15.
 */
public class SSJobConf extends JobConf{

	@Override
	public void initialize() {
		JobConf.setMapKeyInputClassName("impl.LongWritable");
		JobConf.setMapValueInputClassName("impl.StringWritable");
		JobConf.setMapKeyOutputClassName("impl.StringWritable");
		JobConf.setMapValueOutputClassName("impl.StringWritable");
		JobConf.setMapperClassName("populationExtractInfo.SSMapper");
		JobConf.setReducerClassName("populationExtractInfo.SSReducer");


	}
}
