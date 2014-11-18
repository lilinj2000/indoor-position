#! /usr/bin/python
import re
import os

dataPattern = re.compile(r'''^BSSID: #  beginning with BSSID:
\s
\b(b4:b5:2f:4d:..:..)\b         # the mac address
.*
level:
\s
(-?\d+)                         # the level value
.*
''', re.VERBOSE)

macAPMap = { "b4:b5:2f:4d:61:00": "AP41",
             "b4:b5:2f:4d:61:01": "AP41",
             "b4:b5:2f:4d:61:10": "AP41",
             "b4:b5:2f:4d:61:11": "AP41",

             "b4:b5:2f:4d:51:00": "AP42",
             "b4:b5:2f:4d:51:01": "AP42",
             "b4:b5:2f:4d:51:10": "AP42",
             "b4:b5:2f:4d:51:11": "AP42",

             "b4:b5:2f:4d:c0:20": "AP43",
             "b4:b5:2f:4d:c0:21": "AP43",
             "b4:b5:2f:4d:c0:30": "AP43",
             "b4:b5:2f:4d:c0:31": "AP43"}

apValueMap = { "AP41": [0, 0, 0],
               "AP42": [0, 0, 0],
               "AP43": [0, 0, 0]}

f = open('1_1.txt', 'r')


location = tuple(os.path.splitext(f.name)[0].split('_'))
# print '%s\t%s' % location

for line in f:
    result = dataPattern.search(line)
    if result:
        # print '%s' % result.group(1)
        if  macAPMap.has_key(result.group(1)):
            # print '%s\t%s' % result.groups()
            apValueMap[macAPMap[result.group(1)]][0] += int(result.group(2))
            apValueMap[macAPMap[result.group(1)]][1] += 1;
    
for k, v in apValueMap.items():
    if v[1]!=0:
        apValueMap[k][2] = v[0]/v[1]

print apValueMap


print '%s  %s' % location,

for k in sorted(apValueMap):
    print '%d' % apValueMap[k][2],
print


