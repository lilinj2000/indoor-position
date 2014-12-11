#!/usr/bin/env python
# coding: utf-8

"""configure the ap info"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2014/12/11 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"


import json

def config_ap():
    """generate the ap config info"""
    
    ap_mps01 = {"SSID": "MPS01", "MAC": ["28:2c:b2:5a:8c:be"]}
    ap_mps02 = {"SSID": "MPS02", "MAC": ["28:2c:b2:5a:6b:2c"]}

    ap_41 = {"SSID": "AP41", "MAC": ["b4:b5:2f:4d:61:00", 
                                     "b4:b5:2f:4d:61:01", 
                                     "b4:b5:2f:4d:61:10", 
                                     "b4:b5:2f:4d:61:11"]} 

    ap_42 = {"SSID": "AP42", "MAC": ["b4:b5:2f:4d:51:00", 
                                     "b4:b5:2f:4d:51:01", 
                                     "b4:b5:2f:4d:51:10", 
                                     "b4:b5:2f:4d:51:11"]} 

    ap_43 = {"SSID": "AP43", "MAC": ["b4:b5:2f:4d:c0:20", 
                                     "b4:b5:2f:4d:c0:21", 
                                     "b4:b5:2f:4d:c0:30", 
                                     "b4:b5:2f:4d:c0:31"]} 

    aps = dict((item["SSID"], item) for item in 
               [ap_mps01, ap_mps02, ap_41, ap_42, ap_43])
    
    # print aps
    # print json.dumps(aps, sort_keys=True, indent=4)
    with open("ap.cfg", "w") as ap_file:
        json.dump(aps, ap_file, sort_keys=True, indent=4)
        ap_file.close()

if __name__ == "__main__":
    config_ap()
