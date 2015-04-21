package ProjFibo;
import api.JobConf;

public class SSJobConf extends JobConf {

	@Override
	public void initialize(){
		JobConf.setMapKeyInputClassName("impl.LongWritable");
		JobConf.setMapValueInputClassName("impl.StringWritable");
		JobConf.setMapKeyOutputClassName("impl.StringWritable");
		JobConf.setMapValueOutputClassName("impl.StringWritable");
        /*
        JobConf.setReduceKeyOutputClassName("impl.StringWritable");
        JobConf.setReduceValueOutputClassName("impl.FloatWritable");
        */
		JobConf.setMapperClassName("Fibo.SSMapper");
		JobConf.setReducerClassName("Fibo.SSReducer");

	}
	public static void main(String[] args) throws Exception {
		/*if (args.length != 2) {
			System.err
					.println("Usage: Median <input path> <output path>");
			System.exit(-1);
		}*/
		String input="/home/vishal/Desktop/testFib.txt";
		String op="/home/vishal/Desktop/testFibOp";
		Job job = new Job();
		job.setJarByClass(SSJobConf.class);
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