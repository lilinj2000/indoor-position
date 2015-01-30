#! /usr/bin/python

import re
import os
import glob

dataPattern = re.compile(r'''^BSSID: #  beginning with BSSID:
\s
\b(..:..:..:..:..:..)\b         # the mac address
.*
level:
\s
(-?\d+)                         # the level value
.*
''', re.VERBOSE)

macs = []
with open("0_0.txt") as data_file:
    for line in data_file:
        result = dataPattern.search(line)
        if result:
            #print '%s\t%s' % result.groups()
            if macs.count(result.group(1))==0 :
                macs.append(result.group(1))

macs.sort()

for item in macs:
    print item
