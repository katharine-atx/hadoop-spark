#!/usr/bin/env python

# Task: Calculate total number of viewers by show for ABC given shows/station, show/viewers.
# join1_mapper.py provides the map() function for Map/Reduce

# map() inputs: 6 total files (3 gennum, 3 genchan)
# join2_gennum*.txt consist of <TV show, count> (A TV show title and the number of viewers)
# join2_genchan*.txt consists of <TV show title, channel> (A TV show title and the channel it was on) 

import sys    
   
for line in sys.stdin:
	line       = line.strip()   #strip out carriage return
	key_value  = line.split(",")   #split line, into key and value, returns a list
	key_in     = key_value[0]   #key is first item in list
	value_in   = key_value[1]   #value is 2nd item 
	if value_in == "ABC":
		print(value_in,'\t',key_in) # will be "ABC"\t"show"
	if value_in.isdigit():
		print(key_in,'\t',value_in) # will be "show"\t<viewer_count#>