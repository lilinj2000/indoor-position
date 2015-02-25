#! /usr/bin/python

import re
import os
import glob

dataPattern = re.compile(r'''^BSSID: #  beginning with BSSID:
\s
\b(..:..:..:..:..:..)\b         # the mac address
\s
.*
SSID:(.*)                       # the SSID info
\s
capabilities:
\s
.*
level:
\s
(-?\d+)                         # the level value
.*
''', re.VERBOSE)

# dataPattern = re.compile(r'''^BSSID: #  beginning with BSSID:
# .*
# SSID:(.*)                       # the SSID info
# \s
# capabilities:
# .*
# ''', re.VERBOSE)

# macs = []
aps = {}
for file_name in glob.glob("*.txt"):
    data_file = open(file_name)
# with open("0_0.txt") as data_file:
    for line in data_file:
        result = dataPattern.search(line)
        if result:
            # print '%s\t%s\t%s' % result.groups()
            # print '%s' % result.groups()
            # if macs.count(result.group(1))==0 :
            #     macs.append(result.group(1))
            # print '%s\t%s\n' % (result.group(1), result.group(2))
            if not aps.has_key(result.group(1)):
                aps[result.group(1)] = result.group(2)

# macs.sort()

# for item in macs:
#     print item

# print aps
for key in aps.keys():
    print '%s\t%s' % (key, aps[key])
