# HDFS Word Count Trend Assignment with Map/Reduce - Joining data
# Note: for Cloudera hadoop configuration accessed via VM

# Task: Calculate total number of viewers by show for ABC given shows/station, show/viewers.

# Data Notes:
	# Channels labels have 3 letters
	# TV show titles can appear multiple times, with different counts
	# A TV show and channel combination might appear multiple times
	# TV shows could appear on multiple channels
	# The output should have no commas or punctuation, only 1 space between the TV show title and number
	
# ------------------------------

# Initiating map() and reduce() python scripts plus txt inputs...
gedit make_join2data.py
gedit make_data_join2.txt
gedit join1_mapper.py
gedit join1_reducer.py

# Note: add the script from the referenced .py/.txt files to these
# new objects in the VM environment

# Executing...
chmod +x join1_mapper.py
chmod +x join1_reducer.py
chmod +x make_join2data.py

# Get out of safe mode if necessary so you can make changes on the name node...
hdfs dfsadmin -safemode leave

# Debugging python scripts: Confirm that the mapper script is working ok outside 
# map/reduce, and not using what is in the HDFS file system.
# cat join2_gen*.txt | ./join1_mapperALT.py | sort | ./join1_reducerALT.py
# If these commands work you should see the all the <word, total count>'s , 
# and then you can try running in hadoop. 

# Generating some data inputs
sh make_data_join2.txt

# Copy the input files from local filesystem to the HDFS filesystem. 
hdfs dfs -put /home/cloudera/join2_gen*.txt  /user/cloudera/input

# Confirm files on HDFS...there should be a total of 6 output files
hdfs dfs -ls /user/cloudera/input

# Run the Hadoop streaming command for specified input/output...
hadoop jar /usr/lib/hadoop-mapreduce/hadoop-streaming.jar \
-input /user/cloudera/input \
-output /user/cloudera/output_ALT \
-mapper /home/cloudera/"join1_mapperALT.py" \
-reducer /home/cloudera/"join1_reducerALT.py" \
-numReduceTasks 1

# View the output directory...
hdfs dfs -ls /user/cloudera/output_ALT

# Save output file from join as total_viewer_counts.txt...
hdfs dfs -getmerge /user/cloudera/output_ALT/* total_viewer_counts.txt
