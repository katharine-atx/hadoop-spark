#!/usr/bin/env python

# Word count map() function for Map/Reduce

import sys            
# Processing input to generate key/value pairs....
# stdin is a standard input file
for line in sys.stdin:  
	line = line.strip()  # strip carriage returns
	keys = line.split()  # split at spaces/blanks to create key list
	for key in keys:     
		value = 1  		 # assign a count of 1 for each key instance      
		print('{0}\t{1}'.format(key, value) ) # prints first 2 list items (indexing starts at zero)
#Note: tab is Hadoop default to separate key/value