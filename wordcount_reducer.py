#!/usr/bin/env python

# Word count reduce() function for Map/Reduce

import sys
# Initializing variables...
last_key      = None              
running_total = 0

# Sequential sorting/cross-checking. Strips values and turns into 
# key/value, comparing this_key to last_key. If == value (1) is added
# to running total, else previous running_total is output.
for input_line in sys.stdin:
	input_line = input_line.strip()
	this_key, value = input_line.split("\t", 1) 
	#int() will convert a string to integer (this program does no error checking)
	value = int(value)           
	if last_key == this_key:     
		running_total += value   
	else:
		# if last key is not empty, print it with running_total...
		if last_key:  
			print( "{0}\t{1}".format(last_key, running_total) )
		# and move on to the next key/value...
		running_total = value    
		last_key = this_key
if last_key == this_key:
	print( "{0}\t{1}".format(last_key, running_total)) 