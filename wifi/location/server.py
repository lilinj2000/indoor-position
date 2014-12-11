#!/usr/bin/env python

import json
import logging

import web

import grid


class Location:
    def GET(self):
        logging.info("do GET!!!")

        return "Hello, world!"

    def POST(sef):
        logging.info("do POST!!!")

        try:
            data = web.data()
            logging.info('the data received is %s', data)

            request = json.loads(data)

            loc = grid_file.positioning(request)

            response = {}
            response["x"] = str(loc[0])
            response["y"] = str(loc[1])
            logging.info("the response is %s", repr(response))

            return repr(response)

            # request_json = json.dumps(request, sort_keys=True, indent=4)

        except:
            return web.badrequest();

if __name__=="__main__":
    # logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.DEBUG)
    logging.basicConfig(format='%(asctime)s %(levelname)s:%(message)s', level=logging.INFO)

    urls = (
        '/location', 'Location'
    )

    grid_file = grid.GridFile("gridFile_1209.data")

    app = web.application(urls, globals())
    app.run()
