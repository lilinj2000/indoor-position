#! /bin/bash

sudo docker run --rm -ti -v `pwd`:/usr/src/app lilinj2000/python python $*
