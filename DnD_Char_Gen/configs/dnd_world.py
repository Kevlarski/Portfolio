#!/usr/bin/python

from enum import Enum

class World(Enum):
    LINEAGE = ["Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", \
             "Halfling", "Half-Orc", "Human", "Tiefling"]
    R_SH = ["dra", "dwa", "elf", "gno", "halfelf", "halfling", \
            "halforc", "hum", "tiefling"]
    CLASSES = ["Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", \
        "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"]
    CL_SH = ["barb", "bard", "cler", "dru", "fig", "monk", "pal", "rang", \
        "rogue", "sorc", "warl", "wiz"]
    CLASS_MONEY = {
        "Barbarian":1,
        "Bard":5,
        "Cleric":2,
        "Druid":1,
        "Fighter":4, 
        "Monk":1,
        "Paladin":3,
        "Ranger":2,
        "Rogue":3,
        "Sorcerer":3,
        "Warlock":5,
        "Wizard":5
    }
    ALIG = ["Lawful Good", "Neutral Good", "Chaotic Good", "Lawful Neutral", \
        "True Neutral", "Chaotic Neutral", "Lawful Evil", \
        "Neutral Evil", "Chaotic Evil"]
    ALIG_CHART = [["Lawful", "Neutral", "Chaotic"], \
        ["Good", "Neutral", "Evil"]]
    W_THRESH = [10, 50, 100, 250, 500]
    WEAP_TYPE = {
        "Simple Melee": "configs/config_simple_melee.ini", 
        "Simple Ranged": "configs/config_simple_ranged.ini", 
        "Martial Melee": "configs/config_martial_melee.ini", 
        "Martial Ranged": "configs/config_martial_ranged.ini"
    }
    ARM_TYPE = {
        "Light": "configs/config_armor_light.ini", 
        "Medium": "configs/config_armor_medium.ini", 
        "Heavy": "configs/config_armor_heavy.ini"
    }
    
