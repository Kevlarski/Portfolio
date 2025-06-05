#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sun Jun  1 22:25:14 2025

@author: kevlar
"""

from jinja2 import Environment, FileSystemLoader


def render_template(file_name,entries):
    output_file = "index_test1.html"
    environment = Environment(loader=FileSystemLoader("./templates"))
    template = environment.get_template(file_name)
    
    with open(output_file, mode="w", encoding="utf-8") as output:
        output.write(template.render(entries))