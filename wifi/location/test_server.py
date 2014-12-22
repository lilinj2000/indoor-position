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

        ap_mps01 = {"SSID": "MPS01", "level": -67, "BSSID": "28:2c:b2:5a:8c:be"}
        ap_mps02 = {"SSID": "MPS02", "level": -56, "BSSID": "28:2c:b2:5a:6b:2c"}
        ap_43 = {"SSID": "AP43", "level": -40, "BSSID": "b4:b5:2f:4d:c0:20"}

        req = {}
        req["APInfo"] = [ap_mps01, ap_mps02, ap_43]

        r = testApp.post('/location', repr(req))
        assert_equal(r.status, 200)
        r.mustcontain('"x": "13"')
        r.mustcontain('"y": "5"')
               
        # {"APInfo":[
        # {"frequency":2412,"level":-52,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:02:d1","SSID":"EWA@GUEST"},
        # {"frequency":2412,"level":-54,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:02:d0","SSID":"EWA@ECN"},
        # {"frequency":2437,"level":-71,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:12:90","SSID":"EWA@ECN"},
        # {"frequency":2462,"level":-80,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:22:d0","SSID":"EWA@ECN"},
        # {"frequency":2437,"level":-91,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:d1:f0","SSID":"EWA@ECN"},
        # {"frequency":2412,"level":-79,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:f1:30","SSID":"EWA@ECN"},
        # {"frequency":2462,"level":-85,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:11:b0","SSID":"EWA@ECN"},
        # {"frequency":2412,"level":-83,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:12:d0","SSID":"EWA@ECN"},
        # {"frequency":2437,"level":-65,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:12:91","SSID":"EWA@GUEST"},
        # {"frequency":2462,"level":-74,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:22:d1","SSID":"EWA@GUEST"},
        # {"frequency":2412,"level":-79,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:f1:31","SSID":"EWA@GUEST"},
        # {"frequency":2462,"level":-84,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:11:b1","SSID":"EWA@GUEST"},
        # {"frequency":2437,"level":-83,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:d1:f1","SSID":"EWA@GUEST"},
        # {"frequency":2412,"level":-81,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:12:d1","SSID":"EWA@GUEST"},
        # {"frequency":2462,"level":-83,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:51:70","SSID":"EWA@ECN"},
        # {"frequency":2452,"level":-88,"capabilities":"[WPA-PSK-TKIP+CCMP][WPA2-PSK-TKIP+CCMP][ESS]","BSSID":"78:52:62:0b:43:8c","SSID":"TD316_438C"},
        # {"frequency":2462,"level":-87,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:51:71","SSID":"EWA@GUEST"},
        # {"frequency":2462,"level":-83,"capabilities":"[WPA2-EAP-CCMP][ESS]","BSSID":"b4:b5:2f:4d:21:50","SSID":"EWA@ECN"},
        # {"frequency":2462,"level":-88,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:91:f1","SSID":"EWA@GUEST"},
        # {"frequency":2437,"level":-92,"capabilities":"[ESS]","BSSID":"b4:b5:2f:4d:51:91","SSID":"EWA@GUEST"}]}
