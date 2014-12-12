FROM ubuntu:latest
MAINTAINER Linjiang Li "lilinj2000@gmail.com"
ENV REFRESHED_AT 2014-12-12

# install python
RUN apt-get update && apt-get install -y \
    python \
    python-pip \
    --no-install-recommends 

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY requirements.txt /usr/src/app/
RUN pip install -r requirements.txt


