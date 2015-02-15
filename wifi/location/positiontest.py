#!/usr/bin/env python
# coding: utf-8

"""Unit test for position.py"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2015/2/15 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"

import unittest

import position


class TestLoadWifiConfig(unittest.TestCase):  # pylint: disable=R0904
    """load wifi config from file"""

    def test_normal_wifi_config_file(self):
        """load the normal wifi config file"""

        wifi_config = position.Position()
        # print wifi_config.wifi_cfg_
        # print len(wifi_config.wifi_cfg_)
        self.assertEqual(4, len(wifi_config.wifi_cfg_))


class TestPositioning(unittest.TestCase):  # pylint: disable=R0904
    """positioning test"""

    def setUp(self):
        """setup for Positioning test"""
        self.position_ = position.Position()

    def test_normal_positioning(self):
        """positioning, return location"""

        ap_1 = {"SSID": "MPS01", "level": -67, "BSSID": "b4:b5:2f:4d:51:10"}
        ap_2 = {"SSID": "MPS02", "level": -56, "BSSID": "b4:b5:2f:4d:51:11"}
        ap_3 = {"SSID": "MPS03", "level": -40, "BSSID": "b4:b5:2f:4d:c1:90"}
        ap_4 = {"SSID": "MPS04", "level": -20, "BSSID": "b4:b5:2f:4d:c1:91"}


        req = {}
        req["APInfo"] = [ap_1, ap_2, ap_3, ap_4]

        loc = self.position_.positioning(req)

        exp_loc = "TV"
        self.assertEqual(loc, exp_loc)


if __name__ == "__main__":
    unittest.main()
