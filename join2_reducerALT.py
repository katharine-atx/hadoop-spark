#!/usr/bin/env python

# Task: Calculate total number of viewers by show for ABC given shows/station, show/viewers.
# Provides the reduce() function for Map/Reduce

import sys

# Initialize variables...
ABC_shows = {}
entry_list = []

# Build ABC show dictionary...
for entry in sys.stdin:
	entry = entry.strip()       #strip out carriage return
	key_value = entry.split('\t')   #split line, into key and value, returns a list
	entry_list.append(key_value)
	if entry_list[0] == "ABC":
		# If the show is not already in the ABC show dictionary...
		if ABC_shows.has_key(key_value[1]) == False:
			# Add it to the dictionary...
            ABC_dict.update({key_value[1]:0})

# Look-up loop across all show/viewer entries...
for key_value in entry_list:	
	# If the show is on ABC...
	if ABC_shows.has_key(key_value[0]):
		# Incrementally add viewers to "value" (key_value[1]) while looping
		ABC_shows[key_value[0]]+=int(key_value[1])

# Print out 
for key,value in ABC_shows.iteritems():
    print(key," ",value)   