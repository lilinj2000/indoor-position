
import sys
import json
import math
import logging


class GridFile:
    """
    the class initial the grid file,
    and do positioning base on the grid info
    """

    def __init__(self, file_name):
        """initial the grid file, load the file to memory"""

        self.aps = ("AP43", "MPS01", "MPS02")

        self.grids = []
        with open(file_name) as fd:
            for line in fd:
                grid_list = [int(item) for item in (line.split())]
                self.grids.append(tuple(grid_list))

            fd.close()

    def positioning(self, request):
        """just do positioning base on the request"""

        logging.debug('do positioning!!!')

        sig = self.fetch_ap_value(request)
        logging.debug('the sig is %s', repr(sig))

        min_dist = sys.maxint
        logging.debug('the min dist is %d', min_dist)

        loc = []

        for grid in self.grids:
            dist = self.distance([sig[key] for key in sorted(sig)], grid[2:])
            logging.debug('the dist is %d', dist)

            if dist < min_dist:
                min_dist = dist
                loc = grid[0:2]

        logging.debug('the min dist is %d, the loc is %s', min_dist, repr(loc))

        return loc

    def distance(self, vec1, vec2):
        """calcualte the dist between two vecs"""

        logging.debug('the vec1 is %s, the vec2 is %s', repr(vec1), repr(vec2))
        vec = map(lambda x, y: x-y, vec1, vec2)
        logging.debug('the vec1-vec2 is %s', repr(vec))

        dist_square = reduce(lambda x, y: x + y**2, vec, 0)
        logging.debug('the dist**2 is %d', dist_square)

        return math.sqrt(dist_square)

    def fetch_ap_value(self, request):
        """fetch the ap signal value"""

        sig = {}
        for ap in request["APS"]:
            ap_id = ap["SSID"]
            ap_sig = int(ap["SIG"])
            if ap_id in self.aps:
                sig[ap_id] = ap_sig

        return sig

if __name__ == "__main__":
    grid = GridFile("gridFile_1209.data")
    print grid.grid

    req_string = '''
                {"APS":
                 [
                     {"SSID": "MPS01", "SIG": "-67"},
                     {"SSID": "MPS02", "SIG": "-56"},
                     {"SSID": "AP43", "SIG": "-40"}
                 ]
                 }'''
    request = json.loads(req_string)

    sig = grid.fetch_ap_value(request)
    print sig
