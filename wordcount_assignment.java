# HDFS Word Count Assignment with Map/Reduce
# Note: for Cloudera hadoop configuration accessed via VM

# Initiating map() and reduce() python scripts...
gedit wordcount_mapper.py
gedit wordcount_reducer.py

# Note: add the script from the referenced .py files to these
# new objects in the VM environment

# Viewing files....
more wordcount_mapper.py
more wordcount_reducer.py

# Executing...
chmod +x wordcount_mapper.py
chmod +x wordcount_reducer.py

# Confirm working directory
pwd

# Creating test data... replace home/cloudera with working directory
echo "A long time ago in a galaxy far far away" > /home/cloudera/testfile1
echo "Another episode of Star Wars" > /home/cloudera/testfile2

# Get out of safe mode if necessary so you can make changes on the name node...
hdfs dfsadmin -safemode leave

# Debugging python scripts: Confirm that the mapper script is working ok outside 
# map/reduce, and not using what is in the HDFS file system.
# cat testfile1 | ./ wordcount_mapper.py | sort
# cat testfile* | ./ wordcount_mapper.py | sort | ./ wordcount_reducer.py
# If these commands work you should see the all the <word, total count>'s , 
# and then you can try running in hadoop. 

# Create a directory on the HDFS file system...
hdfs dfs -mkdir /user/cloudera/input

# Copy the files from local filesystem to the HDFS filesystem...
hdfs dfs -put /home/cloudera/testfile1 /user/cloudera/input
hdfs dfs -put /home/cloudera/testfile2 /user/cloudera/input

# Confirm files on HDFS...
hdfs dfs -ls /user/cloudera/input

# Run the Hadoop WordCount example with the input and output specified...
hadoop jar /usr/lib/hadoop-mapreduce/hadoop-streaming.jar \
   -input /user/cloudera/input \
   -output /user/cloudera/output_new \
   -mapper /home/cloudera/"wordcount_mapper.py" \
   -reducer /home/cloudera/"wordcount_reducer.py" 

# View the output directory...
hdfs dfs -ls /user/cloudera/output_new

# Look at the files there and check out the contents...
hdfs dfs -cat /user/cloudera/output_new/part-00000

# Change # of reduce tasks to 0...
hadoop jar /usr/lib/hadoop-mapreduce/hadoop-streaming.jar \
   -input /user/cloudera/input \
   -output /user/cloudera/output_new \
   -mapper /home/cloudera/"wordcount_mapper.py" \
   -reducer /home/cloudera/"wordcount_reducer.py" \
   -numReduceTasks 0
   # change to 2 for quiz

# Save output file from this run...
hdfs dfs -getmerge /user/cloudera/output_new/* wordcount_num0_output.txt
