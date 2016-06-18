# PySpark join assignment: Simple Join
# Note: for Cloudera hadoop configuration accessed via VM

# Task: Join fileA / fileB on key with PySpark

# Initiating txt inputs for join...
gedit join1_FileA.txt
gedit join1_FileB.txt

# Get out of safe mode if necessary so you can make changes on the name node...
hdfs dfsadmin -safemode leave

# If necessary, install via: sudo easy_install ipython == 1.2.1 \ PYSPARK_DRIVER_PYTHON = ipython pyspark

# Shift to PySpark...
pyspark

# Load files...
fileA = sc.textFile("file:///home/cloudera/join1_FileA.txt")
fileB = sc.textFile("file:///home/cloudera/join1_FileB.txt")

# Confirm...(use take(#lines) to print out head of larger files)
fileA.collect()
fileB.collect()

# Define PySpark mapper for fileA...
def split_fileA(line):
    # split the input line in word and count on the comma
    word, count = line.split(',')
    # turn the count to an integer  
    count = int(count)
    return (word, count)
	
# Testing fileA mapper... should return Out[]: ('able', 991)
test_line = "able,991"
split_fileA(test_line)

# Define PySpark mapper for fileB...
def split_fileB(line):
    # split the input line into word, date and count_string
    date, word_count = line.split(' ')
    word, count_string = word_count.split(',')
    return (word, date + " " + count_string) 

# Run mappers...
fileA_data = fileA.map(split_fileA)
fileB_data = fileB.map(split_fileB)

# Collect outputs to console...
fileA_data.collect()
fileB_data.collect()

# Run the join and collect output...
fileB_joined_fileA = fileB_data.join(fileA_data)
fileB_joined_fileA.collect()