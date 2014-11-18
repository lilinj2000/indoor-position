#! /usr/bin/python
import re
import os

dataPattern = re.compile(r'''^BSSID: #  beginning with BSSID:
\s
\b(..:..:..:..:..:..)\b         # the mac address
.*
level:
\s
(-?\d+)                         # the level value
.*
''', re.VERBOSE)


f = open('1_1.txt', 'r')

location = tuple(os.path.splitext(f.name)[0].split('_'))

for line in f:
    result = dataPattern.search(line)
    if result:
        print '%s\t%s\t%s\t%s' % (location + result.groups())
    
    
