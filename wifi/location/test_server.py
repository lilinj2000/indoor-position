#!/usr/bin/env python
# coding: utf-8

"""Unit test for server.py"""

__author__ = "Linjiang Li (lilinj2000@gmail.com)"
__version__ = "$Revision: 1.0 $"
__date__ = "$Date: 2014/12/12 14:40:20 $"
__copyright__ = "Copyright (c) 2001 Linjiang Li"
__license__ = "Python"

from paste.fixture import TestApp
from nose.tools import *
from server import app


class TestServer():
    def test_location_get(self):
        middleware = []
        testApp = TestApp(app.wsgifunc(*middleware))
        r = testApp.get('/location')
        assert_equal(r.status, 200)
        r.mustcontain('hello world!')

    def test_location_post(self):
        middleware = []
        testApp = TestApp(app.wsgifunc(*middleware))

        ap_mps01 = {"SSID": "MPS01", "SIG": "-67"}
        ap_mps02 = {"SSID": "MPS02", "SIG": "-56"}
        ap_43 = {"SSID": "AP43", "SIG": "-40"}

        req = {}
        req["APS"] = [ap_mps01, ap_mps02, ap_43]

        r = testApp.post('/location', repr(req))
        assert_equal(r.status, 200)
        r.mustcontain('"x": "13"')
        r.mustcontain('"y": "5"')
               

