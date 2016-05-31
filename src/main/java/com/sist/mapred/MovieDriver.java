package com.sist.mapred;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Component;
@Component("md")
public class MovieDriver {
   @Autowired
   private Configuration conf;
   @Autowired
   private JobRunner jobRunner;
   public void movieMapReuce()
   {
	   try
	   {
		    /*Configuration conf=
		    		new Configuration();
		    Job job=new Job(conf,"MovieCount");
		    job.setJarByClass(MovieDriver.class);
		    job.setMapperClass(MovieMapper.class);
		    job.setReducerClass(MovieReducer.class);
		    
		    job.setOutputKeyClass(Text.class);
		    job.setOutputValueClass(IntWritable.class);
		    
		    FileInputFormat.addInputPath(job, new Path("/home/sist/bigdataStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MapReduceWebProject/desc.txt"));
		     
		    File dir=new File("/home/sist/bigdataStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MapReduceWebProject/output");
		    if(dir.exists())
			 {
				 File[] files=dir.listFiles();
				 for(File f:files)
				 {
					 f.delete();
				 }
				 dir.delete();
			 }
		    
		    FileOutputFormat.setOutputPath(job, new Path("/home/sist/bigdataStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MapReduceWebProject/output"));
		    
		    job.waitForCompletion(true);*/
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
   }
   public void jobCall()
	 {
		 try
		 {
		   jobRunner.call();
		 }catch(Exception ex){}
	 }
	 public void copyFromLocal()
	 {
		 try
		 {
			 FileSystem fs=FileSystem.get(conf);
			 fs.copyFromLocalFile(new Path("/home/sist/bigdataStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringWebHadoopProject/desc.txt"), new Path("/input/desc.txt"));
			 fs.close();
		 }catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
	 }
	 public void copyToLocal()
	 {
		 try
		 {
			 System.out.println("jobRunner:"+jobRunner);
			 FileSystem fs=FileSystem.get(conf);
			 File file=new File("/home/sist/part-r-00000");
			 if(file.exists())
			 {
				 file.delete();
			 }
			 fs.copyToLocalFile(new Path("/output/part-r-00000"), new Path("/home/sist/part-r-00000"));
			 fs.close();
			 
		 }catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
	 }
	 public void fileRemove()
	 {
		 try
		 {
			 FileSystem fs=FileSystem.get(conf);
			 if(fs.exists(new Path("/output")))
			 {
				 fs.delete(new Path("/output"),true);
			 }
			 if(fs.exists(new Path("/input")))
			 {
				 fs.delete(new Path("/input"),true);
			 }
		 }catch(Exception ex)
		 {
			 System.out.println(ex.getMessage());
		 }
	 }
}




