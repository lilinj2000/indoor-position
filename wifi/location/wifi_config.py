#!/usr/bin/env python
# coding: utf-8

"""configure the wifi info"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2015/2/15 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"


import json


def config_wifi():
    """generate the ap config info"""

    # mac_1 = {"Mac": "b4:b5:2f:4d:51:11", "Boundary_of_Level": -62,
    #          "Probability_of_TV": 0.1, "Probability_of_RDS": 0.9,
    #          "Weight": 1}
    # mac_2 = {"Mac": "b4:b5:2f:4d:51:10", "Boundary_of_Level": -61,
    #          "Probability_of_TV": 0.1, "Probability_of_RDS": 0.9,
    #          "Weight": 1}
    # mac_3 = {"Mac": "b4:b5:2f:4d:c1:91", "Boundary_of_Level": -79,
    #          "Probability_of_TV": 0.2, "Probability_of_RDS": 0.9,
    #          "Weight": 1}
    # mac_4 = {"Mac": "b4:b5:2f:4d:c1:90", "Boundary_of_Level": -79,
    #          "Probability_of_TV": 0.2, "Probability_of_RDS": 0.9,
    #          "Weight": 1}
    # wifi_all = dict((item["Mac"], item) for item in
    #                 [mac_1, mac_2, mac_3, mac_4])


    mac_1 = {"Mac": "b4:b5:2f:4d:61:10", 
             "RDS": {"mu": -66.0135, "sigma": 4.58163},
             "TV": {"mu": -61.3372, "sigma": 1.53547},
             "Weight": 1}

    mac_2 = {"Mac": "b4:b5:2f:4d:51:11", 
             "RDS": {"mu": -58.2652, "sigma": 2.5275},
             "TV": {"mu": -54.025, "sigma": 4.13193},
             "Weight": 1}

    mac_3 = {"Mac": "b4:b5:2f:4d:51:10", 
             "RDS": {"mu": -58.2508, "sigma": 2.51507},
             "TV": {"mu": -53.8767, "sigma": 4.03881},
             "Weight": 1}

    mac_4 = {"Mac": "b4:b5:2f:4d:c0:30", 
             "RDS": {"mu": -55.388, "sigma": 4.48858},
             "TV": {"mu": -62.335, "sigma": 4.32519},
             "Weight": 1}

    mac_5 = {"Mac": "b4:b5:2f:4d:c0:31", 
             "RDS": {"mu": -55.3712, "sigma": 4.45931},
             "TV": {"mu": -62.4317, "sigma": 4.08654},
             "Weight": 1}

    wifi_all = dict((item["Mac"], item) for item in
                    [mac_1, mac_2, mac_3, mac_4, mac_5])

    # print aps
    # print json.dumps(aps, sort_keys=True, indent=4)
    with open("wifi.cfg", "w") as wifi_file:
        json.dump(wifi_all, wifi_file, sort_keys=True, indent=4)
        wifi_file.close()

if __name__ == "__main__":
    config_wifi()
