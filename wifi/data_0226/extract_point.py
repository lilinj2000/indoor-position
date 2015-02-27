#! /usr/bin/python

import re
import os
import glob
import xml.etree.ElementTree as ET
from xml.etree import ElementTree
from xml.dom import minidom

def prettify(elem):
    """Return a pretty-printed XML string for the Element.
    """
    rough_string = ElementTree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")


dataPattern = re.compile(r'''^BSSID: #  beginning with BSSID:
\s
\b(..:..:..:..:..:..)\b         # the mac address
.*
level:
\s
(-?\d+)                         # the level value
.*
''', re.VERBOSE)


source_data_file = 'tv_0226_3.txt'
specific_location = 'tv_3'
output_xml_file = '{}.xml'.format(specific_location)

root = ET.Element('measurements')
root.set('location', specific_location)

with open(source_data_file) as data_file:
    for line in data_file:

        if line.find("collection")>0:
            # the point begin
            point = ET.SubElement(root, 'point')
            continue

        result = dataPattern.search(line)
        if result:
            # grid_file.write('{}\t{}\n'.format(result.group(1), result.group(2)))
            ap = ET.SubElement(point, 'ap')
            ap.set('mac', result.group(1))
            ap.set('level', result.group(2))

            #print '%s\t%s' % result.groups()
            # if macs.count(result.group(1))==0 :
            #     macs.append(result.group(1))

# macs.sort()

# for item in macs:
#     print item

# print prettify(root)
with open(output_xml_file, "w") as xml_file:
    xml_file.write('{}'.format(prettify(root)))

