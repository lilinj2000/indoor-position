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

# macs = []
grid_file = open("filter_tv_0216_3", "w")

with open("tv_0216_3.txt") as data_file:
    for line in data_file:
        result = dataPattern.search(line)
        if result:
            grid_file.write('{}\t{}\n'.format(result.group(1), result.group(2)))
            #print '%s\t%s' % result.groups()
            # if macs.count(result.group(1))==0 :
            #     macs.append(result.group(1))

# macs.sort()

# for item in macs:
#     print item
