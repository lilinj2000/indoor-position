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
            # print '%s' % result.group(1)
            # print '%s' % result.group(2)
            if not aps.has_key(result.group(1)):
                aps[result.group(1)] = result.group(2)

# macs.sort()

# for item in macs:
#     print item

# print aps
mac_file = open("mac_info", "wb")
for key in aps.keys():
    # print '%s %s' % (key, aps[key])
    # print '%s' % key
    # print '%s' % aps[key]
    # mac_file.write('{}\t{}\n'.format(key, aps[key]))
    # mac_file.write('{}\n'.format(key))
    mac_file.write('{}\t{}\n'.format(aps[key], key))
    # ap_file.write('\t')
    # ap_file.write('{}\n'.format(aps[key]))
    


