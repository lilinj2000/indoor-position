#! /usr/bin/python

import re
import os
import glob

dataPattern = re.compile(r'''^BSSID: #  beginning with BSSID:
\s
\b(b4:b5:2f:4d:c0:..|28:2c:b2:5a:..:..)\b         # the mac address
.*
level:
\s
(-?\d+)                         # the level value
.*
''', re.VERBOSE)

# macAPMap = { "b4:b5:2f:4d:61:00": "AP41",
#              "b4:b5:2f:4d:61:01": "AP41",
#              "b4:b5:2f:4d:61:10": "AP41",
#              "b4:b5:2f:4d:61:11": "AP41",

#              "b4:b5:2f:4d:51:00": "AP42",
#              "b4:b5:2f:4d:51:01": "AP42",
#              "b4:b5:2f:4d:51:10": "AP42",
#              "b4:b5:2f:4d:51:11": "AP42",
macAPMap = {
             "b4:b5:2f:4d:c0:20": "AP43",
             "b4:b5:2f:4d:c0:21": "AP43",
             "b4:b5:2f:4d:c0:30": "AP43",
             "b4:b5:2f:4d:c0:31": "AP43",

             "28:2c:b2:5a:8c:be": "MPS01",
             "28:2c:b2:5a:6b:2c": "MPS02" }

apValueMap = { "MPS01": [0, 0, 0],
               "MPS02": [0, 0, 0],
               "AP43": [0, 0, 0]}

# apValueMap = { "AP41": [0, 0, 0],
#                "AP42": [0, 0, 0],
#                "AP43": [0, 0, 0]}

gridFile = open("gridFile_1209.data", "w");
apFile = open("apFile_1209.data", "w");

# keys = sorted(apValueMap)
# gridFile.write('x\ty\t{}\t{}\t{}\n'.format(keys[0], keys[1], keys[2]))


for fileName in glob.glob("*.txt"):
    f = open(fileName, 'r')
    
    location = tuple(fileName.split('.')[0].split('_'))

    for line in f:
        result = dataPattern.search(line)
        if result:
            # print '%s' % result.group(1)
            if  macAPMap.has_key(result.group(1)):
                # print '%s\t%s' % result.groups()
                apValueMap[macAPMap[result.group(1)]][0] += int(result.group(2))
                apValueMap[macAPMap[result.group(1)]][1] += 1;

                # print the location and the AP info
                apFile.write('{0}\t{1}'.format(location[0], location[1]))
                numAP = re.search('(\d+)', macAPMap[result.group(1)]).group(0)
                # print numAP
                apFile.write('\t{}\t{}\n'.format(numAP, result.group(2)))
                # print '%s\t%s' % location,
                # print '\t%s\t%d' % (macAPMap[result.group(1)], int(result.group(2)))
                
    for k, v in apValueMap.items():
        if v[1]!=0:
            apValueMap[k][2] = v[0]/v[1]

    # print apValueMap

    gridFile.write('{}\t{}'.format(location[0], location[1]))
    # print '%s\t%s' % location,

    # print sorted(apValueMap)
    for k in sorted(apValueMap):
        gridFile.write('\t{}'.format(apValueMap[k][2]))
        # print '\t%d' % apValueMap[k][2],
    # print
    gridFile.write('\n')


gridFile.close()
apFile.close()
