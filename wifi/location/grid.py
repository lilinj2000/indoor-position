#!/usr/bin/env python
# coding: utf-8

"""store grids, and match the grid"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2014/12/11 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"


import sys
import math
import logging


class GridFile(object):
    """
    the class initial the grid file,
    and do positioning base on the grid info
    """

    def __init__(self, file_name):
        """initial the grid file, load the file to memory"""

        self.aps_ = ("AP43", "MPS01", "MPS02")

        self.grids_ = []
        with open(file_name) as grid_file:
            for line in grid_file:
                grid_list = [int(item) for item in line.split()]
                self.grids_.append(tuple(grid_list))

            grid_file.close()

    def positioning(self, request):
        """just do positioning base on the request"""

        logging.debug('do positioning!!!')

        sig = self.fetch_ap_value(request)
        logging.debug('the sig is %s', repr(sig))

        min_dist = sys.maxint
        logging.debug('the min dist is %d', min_dist)

        loc = []

        for grid in self.grids_:
            dist = self.distance([sig[key] for key in sorted(sig)], grid[2:])
            logging.debug('the dist is %d', dist)

            if dist < min_dist:
                min_dist = dist
                loc = grid[0:2]

        logging.debug('the min dist is %d, the loc is %s', min_dist, repr(loc))

        return loc

    @classmethod
    def distance(cls, vec1, vec2):
        """calcualte the dist between two vecs"""

        logging.debug('the vec1 is %s, the vec2 is %s', repr(vec1), repr(vec2))
        vec = [x-y for x, y in zip(vec1, vec2)]
        logging.debug('the vec1-vec2 is %s', repr(vec))

        dist_square = reduce(lambda x, y: x + y**2, vec, 0)
        logging.debug('the dist**2 is %d', dist_square)

        return math.sqrt(dist_square)

    def fetch_ap_value(self, request):
        """fetch the ap signal value"""

        sig = {}
        for ap_info in request["APS"]:
            ap_id = ap_info["SSID"]
            ap_sig = int(ap_info["SIG"])
            if ap_id in self.aps_:
                sig[ap_id] = ap_sig

        return sig
