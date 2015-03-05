import json

with open('rds.msr') as f:
    for line in f:
        tt = json.loads(line)
        print tt
