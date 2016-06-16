#!/usr/bin/env python

# Task: Calculate total number of viewers by show for ABC given shows/station, show/viewers.
# Provides the reduce() function for Map/Reduce

import sys

# Initialize variables...
prev_show = ""
ABC_found = False
total_views	= 0

for entry in sys.stdin:
	entry             = entry.strip()       #strip out carriage return
	curr_show, value  = entry.split('\t')         
	# Output of map() function will generate key/values with either "ABC" or viewer count for value...
	if curr_show != prev_show:
			if ABC_found:
				print(prev_show," ",total_views)
				# And then reset defaults...
			total_views = 0
			ABC_found = False
	prev_show = curr_show
	if value == "ABC":
		ABC_found = True
	else:
		total_views += int(value_in)
if ABC_found:
	print(prev_show," ",total_views)
	
    

