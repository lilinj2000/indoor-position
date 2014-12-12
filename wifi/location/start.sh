#! /bin/bash

sudo docker run --rm -ti -v `pwd`:/usr/src/app --expose=8080 -p 8080:8080 lilinj2000/python python server.py
