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


class StoreGrids(unittest.TestCase):
    """load grids from file"""

    def testNormalGridsFile(self):
        """load the normal grid file"""
    
        normal_grid_file = grid.GridFile("gridFile_1209.data")
        self.assertEqual(125, len(normal_grid_file.grids_))


class MatchGrids(unittest.TestCase):
    """match grids"""
    
    def testNormalMatch(self):
        """normal match, return location"""
        self.assertTrue(True)


if __name__ == "__main__":
    unittest.main()
