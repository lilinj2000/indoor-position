#!/usr/bin/env python
# coding: utf-8

"""do positioning base on the probability stat info"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2015/2/15 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"


import sys
import json
import math
import logging

from stdnormdist import *


class Position(object):
    """
    the class initial the wifi config information,
    and do positioning base on the probability info
    """

    def __init__(self):
        """initial the wifi config file, load it to memory"""

        with open("wifi.cfg") as wifi_file:
            self.wifi_cfg_ = json.load(wifi_file)
            wifi_file.close()


    def positioning(self, request):
        """just do positioning base on the request"""

        logging.debug('do positioning!!!')

        # start calculate the porobability each platform
        prob_of_tv_t = 0
        prob_of_rds_t = 0
        for ap_info in request["APInfo"]:
            # ap_id = ap_info["SSID"]
            mac = ap_info["BSSID"]
            level = ap_info["level"]

            if mac in self.wifi_cfg_:
                rds_mu = self.wifi_cfg_[mac]["RDS"]["mu"]
                rds_sigma = self.wifi_cfg_[mac]["RDS"]["sigma"]
                tv_mu = self.wifi_cfg_[mac]["TV"]["mu"]
                tv_sigma = self.wifi_cfg_[mac]["TV"]["sigma"]
                weight = self.wifi_cfg_[mac]["Weight"]

                logging.debug('mac [%s], level [%d]', mac, level)
                logging.debug('RDS (mu, sigma) -  (%f, %f)', rds_mu, rds_sigma)
                logging.debug('TV (mu, sigma) -  (%f, %f)', tv_mu, tv_sigma)
                logging.debug('weight (%f)', weight)

                rds_z = (level-rds_mu)/rds_sigma
                tv_z = (level-tv_mu)/tv_sigma

                rds_z_1 = (level-1-rds_mu)/rds_sigma
                tv_z_1 = (level-1-tv_mu)/tv_sigma

                prob_of_rds = stdnormdist(rds_z) - stdnormdist(rds_z_1)
                prob_of_tv = stdnormdist(tv_z) - stdnormdist(tv_z_1)
                logging.debug('(prob_of_rds, prob_of_tv) -  (%f, %f)', prob_of_rds, prob_of_tv)

                prob_of_rds_t = prob_of_rds_t + prob_of_rds*weight
                prob_of_tv_t = prob_of_tv_t + prob_of_tv*weight

                # prob_of_tv = self.wifi_cfg_[mac]["Probability_of_TV"]
                # prob_of_rds = self.wifi_cfg_[mac]["Probability_of_RDS"]

                # weight_of_tv = self.wifi_cfg_[mac]["Weight"]
                # weight_of_rds = self.wifi_cfg_[mac]["Weight"]

                # boundary_of_level = self.wifi_cfg_[mac]["Boundary_of_Level"]

                # logging.debug('mac [%s], level [%d]', mac, level)
                # logging.debug('prob_of_tv vs prob_of_rds (%f, %f)', prob_of_tv, prob_of_rds)
                # logging.debug('weight_of_tv vs weight_of_rds (%d, %d)', weight_of_tv, weight_of_rds)
                # logging.debug('boundary_of_level [%d]', boundary_of_level)

                # if level<=boundary_of_level:
                #     prob_of_tv_t = prob_of_tv_t + prob_of_tv*weight_of_tv
                #     prob_of_rds_t = prob_of_rds_t + prob_of_rds*weight_of_rds
                # else:
                #     prob_of_tv_t = prob_of_tv_t + (1-prob_of_tv)*weight_of_tv
                #     prob_of_rds_t = prob_of_rds_t + (1-prob_of_rds)*weight_of_rds


        # after the probability calculation finished
        logging.debug('prob_of_tv_t vs prob_of_rds_t (%f, %f)', prob_of_tv_t, prob_of_rds_t) 

        loc = "TV"
        if prob_of_tv_t<prob_of_rds_t:
            loc = "RDS"

        logging.debug('the location is %s', loc)

        return loc

