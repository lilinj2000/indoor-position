#!/usr/bin/env python
# coding: utf-8

"""positioning server base on RESTful api"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2014/12/11 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"

import json
import logging

import web

import grid


class Location(object):
    '''
    the location service
    '''

    def __init__(self):
        """the init function"""

        self.hello_ = "hello world!"
        self.grid_ = grid.GridFile("gridFile_1209.data")

    def GET(self):  # pylint: disable=C0103
        """http get"""
        logging.info("do GET!!!")

        return self.hello_

    def POST(self):  # pylint: disable=C0103
        """http post"""

        logging.info("do POST!!!")

        data = web.data()
        logging.info('the data received is %s', data)

        request = json.loads(data)

        loc = self.grid_.positioning(request)

        response = {}
        response["x"] = str(loc[0])
        response["y"] = str(loc[1])
        logging.info("the response is %s", repr(response))

        return repr(response)

if __name__ == "__main__":
    # logging.basicConfig(format='%(levelname)s:%(message)s',
    # level=logging.DEBUG)
    logging.basicConfig(format='%(asctime)s %(levelname)s:%(message)s',
                        level=logging.INFO)

    URLS = (
        '/location', 'Location'
    )

    APP = web.application(URLS, globals())
    APP.run()
