#!/usr/bin/env python
'''
 this is the positioning server
'''

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

    def get(self):
        """http get"""
        logging.info("do GET!!!")

        return self.hello_

    def post(self):
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
