import json
import sys

class GridFile:
    """the class initial the grid file, and do positioning base on the grid info"""

    def __init__(self, file_name):
        """initial the grid file, load the file to memory"""

        self.aps = ("AP43", "MPS01", "MPS02")

        self.grid = []
        with open(file_name) as fd:
            for line in fd:
                self.grid.append( tuple([ int(item) for item in (line.split()) ]) )

            fd.close()

    def positioning(self, request):
        """just do positioning base on the request"""

        sig = fetch_ap_value(request)
        
        min_dist = sys.maxint
        loc = []

        for grid in self.grid:
            dist = self.distance(sig, grid[2:])
            if dist<min_dist:
                min_dist = dist
                loc = grid[0:2]

        return loc

    def distance(vec1, vec2):
        """calcualte the dist between two vecs"""
        vec = map(lambda x, y: x-y, vec1, vec2)
        return math.sqrt(reduce(lambda x, y: x**2+y**2, vec))
    
    def fetch_ap_value(self, request):
        """fetch the ap signal value"""

        sig = {}
        for ap in request["APS"]:
            ap_id = ap["SSID"]
            ap_sig = int(ap["SIG"])
            if ap_id in self.aps:
                sig[ap_id] = ap_sig

        return sig
    
if __name__=="__main__":
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
