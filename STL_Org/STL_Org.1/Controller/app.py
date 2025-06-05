#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jun  1 00:17:47 2025

@author: kevlar
"""
from flask import Flask, render_template
import sys

sys.path.append('/home/kevlar/Documents/code_playground/STL_Org.1/Models')
import Model_test as mTest
import collections
# [This will be the main access point for the stuff that happens]
collections.Sequence = collections.abc.Sequence

app = Flask(__name__)

@app.route("/")
def home():
    template = "index.html" 
    thing_done = mTest.do_the_thing()
    template = thing_done["template"]
    print(thing_done["entries"])
    if thing_done["entries"]+["x"]=="x":
        return render_template(template,thing_done["entries"])
    else: return render_template(template)

@app.route("/update")
def update():
    template = "update.html"
    thing_done = mTest.do_the_thing()
    template = thing_done["template"]
    print(thing_done)
    return render_template(template)
