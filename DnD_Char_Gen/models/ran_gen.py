#!/usr/bin/python

# imports
from configparser import ConfigParser
from copy import deepcopy as dc
from configs.dnd_world import World
import random
import models.die as die
import re

# functions
def rname(r, pos):
    "Pull a random first name from a text file based on race"
    if pos == "First": f_or_l = "f"
    else: f_or_l = "l"
    
    for i in range(len(World.LINEAGE.value)):
        if r == World.LINEAGE.value[i]:
            path = f"names/{World.R_SH.value[i]}_{f_or_l}names.txt"
            name = open(path, "r")
            break
    
    names = name.read().split(';')
    name.close()
    return re.sub("\n", "", random.choice(names))

def rrace(): return random.choice(World.LINEAGE.value)
def rclass(): return random.choice(World.CLASSES.value)
def ralignment(): return random.choices(World.ALIG.value)

def rwealth():
    "Return a random amount of wealth (in GP)"
    return int(die.rolld(World.W_THRESH.value[-1]))

def rarmor():
    "Return a randomly generated article of outside clothing"
    config = ConfigParser()
    config.read(random.choice(list(World.ARM_TYPE.value.values())))
    armor = random.choice(config.sections())
    return armor

def rweapon():
    config = ConfigParser()
    config.read(random.choice(list(World.WEAP_TYPE.value.values())))
    weapon = random.choice(config.sections())
    
    if weapon == "Dart":
        return "a handful of darts"
    else:
        return weapon

def get_wealth_desc(w): 
    "Return a random description of the character's appearance (GP based)"
    wd_txt_name = ["s_poor", "poor", "moderate", "wealthy", "s_wealthy"]
    
    for i in range(len(wd_txt_name)):
        if w <= World.W_THRESH.value[i]:
            txt = open(f"wealth_descs/{wd_txt_name[i]}.txt", "r")
            break
        
    if w > World.W_THRESH.value[-1]:
        txt = open(f"wealth_descs/{wd_txt_name[-1]}.txt", "r")
    
    pot_descs = txt.read().split(";")
    txt.close()
    return re.sub("\n", "", random.choice(pot_descs))
