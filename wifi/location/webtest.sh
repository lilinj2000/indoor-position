#! /bin/bash

sudo docker run --rm -ti -v `pwd`:/usr/src/app -e WEBPY_ENV=test lilinj2000/python nosetests
