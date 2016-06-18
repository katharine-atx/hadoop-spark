# PySpark join assignment: Advanced Join
# Note: for Cloudera hadoop configuration accessed via VM

# Task: Summarize total viewers by TV channel(i.e. ABC, NBC networks). To summarize, we'll join 2 files,
# 1) viewer counts by TV show and 2) TV shows by network.

# Input files will be those generated from the script: join2_mapreduce.java
# Verify files are present.

# Launch PySpark
# pyspark

# Read input text files for TV show viewer counts and shows by network...
show_views_file = sc.textFile("file:///home/cloudera/join2_gennum?.txt")
show_channel_file = sc.textFile("file:///home/cloudera/join2_genchan?.txt")

# Verify first file... should return [u'Hourly_Sports,21', u'PostModern_Show,38']
show_views_file.take(2)

# Parse first file inputs into show, views
def split_show_views(line):
    show, views = line.split(',')
    return (show, views)

# Parse second file inputs into show, channel
def split_show_channel(line):
    show, channel = line.split(',')
    return (show, channel)

# Create parsed dataset(RDD) with map()
show_views = show_views_file.map(split_show_views)
show_channel = show_channel_file.map(split_show_channel)

# Join the RDDs...
joined_dataset = show_views.join(show_channel)
# Verify....
joined_dataset.take(4)

# Extract channel as key...
# Note: show_views_channel contains a tuple at index 1: 'show', (views, 'channel')
def extract_channel_views(show_views_channel): 
    views, channel = show_views_channel[1]
    views = int(views)
    return (channel, views)

# RDD with channel as the key field...
channel_views = joined_dataset.map(extract_channel_views)

# Summing across all channels to get total viewers...
def total_counts(a, b): 
    return a + b

# reduceByKey() for total viewer counts by channel and collect RDD...
channel_views.reduceByKey(total_counts).collect()
