#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat May 31 23:54:02 2025

@author: kevlar
"""
import mysql.connector
from mysql.connector import Error

def create_connection(host_name, user_name, user_password, db_name):
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_name,
            user=user_name,
            passwd=user_password,
            database=db_name
        )
        print("Connection to MySQL DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")

    return connection

def ez_connect():
    connection = create_connection("localhost", "kevlar", "bulletpr00f","STL_Org")
    return connection

def execute_ins_query(connection, query):
    cursor = connection.cursor()
    try:
        cursor.execute(query)
        connection.commit()
        return "Query executed successfully"
    except Error as e:
        return f"The error '{e}' occurred"
        
def execute_read_query(connection, query):
    cursor = connection.cursor()
    result = None
    try:
        cursor.execute(query)
        result = cursor.fetchall()
        return result
    except Error as e:
        return f"The error '{e}' occurred"
        
