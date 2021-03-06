#!/usr/bin/env python
# coding: utf-8

"""Unit test for grid.py"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2014/12/11 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"

import unittest

import grid
import logging

class TestStoreGrids(unittest.TestCase):  # pylint: disable=R0904
    """load grids from file"""

    def test_normal_grids_file(self):
        """load the normal grids file"""

        normal_grid_file = grid.GridFile("gridFile_1209.data")
        self.assertEqual(125, len(normal_grid_file.grids_))


class TestMatchGrids(unittest.TestCase):  # pylint: disable=R0904
    """match grids"""

    def setUp(self):
        """setup for MatchGrids test"""
        self.grid_ = grid.GridFile("gridFile_1209.data")

    def test_normal_match(self):
        """normal match, return location"""

        ap_mps01 = {"SSID": "MPS01", "level": -67, "BSSID": "28:2c:b2:5a:8c:be"}
        ap_mps02 = {"SSID": "MPS02", "level": -56, "BSSID": "28:2c:b2:5a:6b:2c"}
        ap_43 = {"SSID": "AP43", "level": -40, "BSSID": "b4:b5:2f:4d:c0:20"}

        req = {}
        req["APInfo"] = [ap_mps01, ap_mps02, ap_43]

        loc = self.grid_.positioning(req)

        exp_loc = (13, 5)
        self.assertEqual(loc, exp_loc)


if __name__ == "__main__":
    unittest.main()
