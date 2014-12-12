FROM lilinj2000/python:latest
MAINTAINER Linjiang Li "lilinj2000@gmail.com"
ENV REFRESHED_AT 2014-12-12

WORKDIR /usr/src/app

RUN rm -rf *

COPY . /usr/src/app/

EXPOSE 8080

CMD ["python", "server.py"]
