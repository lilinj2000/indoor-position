import web
import json
import GridFile as grid

urls = (
    '/location', 'Location'
)

grid_file = grid.GridFile("gridFile_1209.data")
# print grid_file.grid

class Location:
    def GET(self):
        return "Hello, world!"

    def POST(sef):
        try:
            data = web.data()

            request = json.loads(data)

            loc = grid_file.positioning(request)

            response = "{x:" + str(loc[0]) + "," + "y:" + str(loc[1]) + "}"

            return response

            # request_json = json.dumps(request, sort_keys=True, indent=4)

        except:
            return web.badrequest();

if __name__=="__main__":
    app = web.application(urls, globals())
    app.run()
