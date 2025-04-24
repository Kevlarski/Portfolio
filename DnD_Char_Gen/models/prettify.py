#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Apr  4 23:07:09 2025

@author: kevlar
"""

#Imports
from textwrap import wrap
from configs.dnd_world import World

def clear(): print('\033[H\033[J', end="")
def beautify(s): print(f"\n\n{s}\n\n")
def alert(s): beautify(f"       {s}")
def req_input(): 
    menu_info = input()
    if menu_info.isnumeric():
        return int(menu_info)
    else:
        return menu_info

def prompt(s):
    print(s, end="")
    return req_input()

def format_desc(c):
    "Extra formatting for descriptions"
    stats = [c.str, c.dex, c.con, c.wis, c.int, c.cha]
    form = []
    ind = "                       "
    
    for stat in stats:
        if stat < 10:
            form.append(str(stat).rjust(2, ' '))
        else:
            form.append(stat)
            
    res = f" {ind}|     STR {form[0]}     WIS {form[3]}     |" \
          f"\n {ind}|     DEX {form[1]}     INT {form[4]}     |" \
          f"\n {ind}|     CON {form[2]}     CHA {form[5]}     |"
    
    if c.p_race == "Elf": grammar = "an"
    else: grammar = "a"
    
    if "Dart" in c.p_weapon:
        weapon = "a handful of darts"
    else: weapon = "a " + c.p_weapon
      
    return [res, weapon, grammar]

def print_desc(c):
    "Display a brief, cleanly-formatted description of generated character"
    formatted = format_desc(c)
    stats = formatted[0]
    weapon = formatted[1]
    return_list = []
    char_id = f"{c.p_name}, {str.title(formatted[2])} {c.p_race} {c.p_class} "
    desc = f"{c.p_fname} is {c.p_alignment}, {c.p_age} years old, and " \
        "has a net worth of {:,d} GP.".format(c.p_net_worth)
    breaker = "".center(80, '-')
    appearance = f"{c.p_fname} is wearing {c.p_clothing}, and {c.p_wea_desc}"
    armed = f"{c.p_fname} wields {weapon}."
    
    desc_dict = {
        "   Description: ": desc,
        "   Appearance : ": appearance,
        "   Wielding   : ": armed
    }
    
    return_list.append("\n\n\n" + char_id.center(80, ' '))
    return_list.append(f"{breaker}\n{stats}\n{breaker}\n")
    for key, val in desc_dict.items():
        wrapped = wrap(f"{key}{val}", 77)
        return_list.append(wrapped[0])
        if len(wrapped) > 0:
            for i in range(len(wrapped)-1):
                return_list.append("".ljust(len(key), ' ') + wrapped[i+1])
        return_list[-1] += "\n"
    
    return_string = ""
    for line in return_list:
        return_string += (line + "\n")
    return(return_string)

def display_races():
    "Display races in D&D"
    r_ph = "\n\n"
    for i in range(len(World.LINEAGE.value)):
        r_ph += f"   {i+1}| {World.LINEAGE.value[i]}\n"
    print(f"{r_ph}\n")
    
def display_classes():
    "Display classes in D&D"
    c_ph = "\n\n"
    for i in range(len(World.CLASSES.value)):
        c_ph += f"   {i+1}| {World.CLASSES.value[i]}\n"
    print(f"{c_ph}\n")