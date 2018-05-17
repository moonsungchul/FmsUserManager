# -*- coding:utf-8 -*-
import collections
import bson
import random, array, pymongo
import urllib, urllib2
import requests
from bson.binary import Binary as BsonBinary
import json

class TestRest:

    def __init__(self):
        self.base_url = "http://localhost:8080/"
        
    def deleteREST(self, url):
        req = urllib2.Request(url)
        #req.add_header('Content-Type','application/json')
        req.get_method = lambda: 'DELETE'
        #response = urllib2.urlopen(req, data)
        response = urllib2.urlopen(req)
        ret = json.load(response)
        response.close()
        return ret

    def getJson(self, url):
        req = urllib2.Request(url)
        req.add_header('Content-Type','application/json')
        req.get_method = lambda: 'GET'
        response = urllib2.urlopen(req)
        ret = json.load(response)
        response.close()
        return ret

    def postJson(self, url, data):
        req = urllib2.Request(url, data)
        req.add_header('Content-Type','application/json')
        req.get_method = lambda: 'POST'
        response = urllib2.urlopen(req )
        ret =  json.load(response)
        response.close()
        return ret

    def post(self, url, data):
        req = urllib2.Request(url)
        #req.add_header('Content-Type','/json')
        req.get_method = lambda: 'POST'
        response = urllib2.urlopen(req, data)
        ret =  json.load(response)
        response.close()
        return ret

    def putJson(self, url, data):
        print("url : " +  url)
        req = urllib2.Request(url)
        req.add_header('Content-Type','application/json')
        req.get_method = lambda: 'PUT'
        response = urllib2.urlopen(req, data)
        ret =  json.load(response)
        response.close()
        return ret

    def postMultipart(self, url, data):
        print("url : "+ url)
        fp = open(data, "rb")
        data = {'file': fp}
        response = requests.post(url, files = data)
        #ret = json.load(response.content)
        ret = response.content
        #print ret
        fp.close()
        response.close()
        return ret 

    def login(self):
        url = self.base_url + "user/login"
        user = """
        {
            "userId" : "moonsuchstar@firemstar.com", 
            "name" : "", 
            "password" : "wooag01", 
            "email" : "", 
            "accessToken" : "", 
            "createDate" : "", 
            "modifiedDate" : ""
        }
        """
        self.postJson(url, user)


if __name__ == '__main__':
    rest = TestRest()
    rest.login()
        

     
