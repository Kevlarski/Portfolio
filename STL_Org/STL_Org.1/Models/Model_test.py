#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Jun  2 19:04:38 2025

@author: kevlar
"""

from jinja2 import Environment, FileSystemLoader
import db_connect as db_connect
import collections
import easygui
# [This will be the main access point for the stuff that happens]
collections.Sequence = collections.abc.Sequence

def do_the_thing():
    db = db_connect.ez_connect()
    
    choices = ["SELECT","INSERT","UPDATE"]
    
    choicebx = easygui.choicebox('Testing Options','up top', choices)
    environment = Environment(loader=FileSystemLoader("/home/kevlar/Documents/code_playground/STL_Org.1/templates/"))
    
    match choicebx:
        case "INSERT":
            multibx_msg = "Enter info to be inserted"
            fieldNames = ["Name of File","Zip? (T/F)","STL Category"]
            insert_values = easygui.multenterbox(multibx_msg,"Insert New Row",fieldNames)
            fname = "'"+insert_values[0]+"'"
            zip_bool = insert_values[1]
            cat = "'"+insert_values[2]+"'"
            template = "insert.html"
            insert_query = "INSERT INTO STL_Org.files (file_name, is_zip, category) VALUES ("+fname+","+zip_bool+","+cat+");"
            ins_msg = db_connect.execute_ins_query(db, insert_query)
            return {"template":template,"Msg":ins_msg,"Receipt":insert_values}
            
        case "SELECT":
            template = environment.get_template("select.html")
            select_query = "SELECT * FROM STL_Org.files;"
            read = db_connect.execute_read_query(db, select_query)
            if type(read)=="String":
                return "Error: "+read
            else:
                return {"template":template,"entries":read}
        case "UDATE":
            template = "update.html"
            update_msg = "You changed: "
            update_vals = "update_vals"
            return {"template":template,"Msg":update_msg,"Receipt":update_vals}