#!/usr/bin/python

# imports
from configparser import ConfigParser
from copy import deepcopy as dc
from configs.dnd_world import World
import models.ran_gen as ran_gen
import random
import models.die as die

# functions
def stat_gen(): 
    "Roll 4d6, drop the lowest, return total sum"
    
    rolls = [die.rolld(6), die.rolld(6), die.rolld(6), die.rolld(6)]
    rolls.remove(min(rolls))
            
    return sum(rolls)

def get_alig(alig_list):
    """
        First   [0] is lawful, neutral, chaotic
        Second  [1] is good, neutral, evil
    """
    if alig_list[0] == 2 and alig_list[1] == 2:
        return "True Neutral"
    
    al_li = []
    for i in range(len(alig_list)):
        al_li.append(World.ALIG_CHART.value[i][alig_list[i]-1])
    
    return f"{al_li[0]} {al_li[1]}"

def get_class_ster_nums(cl, data, md, cv):
    """
        cl = class;data = data value to search
        md = mod (how many # per list); cv = should convert to int
    """
    config = ConfigParser()
    config.read("configs/config_races.ini")
    li = []
    li_s = config[cl][data].split(",")
    pos = 0
    
    if cv:
        if md == 1:
            while pos < len(li_s):
                li.append(int(li_s[pos]))
                pos += md
        else:
            while pos < len(li_s):
                li.append([int(li_s[pos]), int(li_s[pos+1])])
                pos += md
        
        return li
    else:
        return li_s

# body
class character:
    
    def __init__(self):
        # character traits
        self.p_race      = ran_gen.rrace()
        self.p_class     = ran_gen.rclass()
        self.p_alig_val  = [die.rolld(3), die.rolld(3)]
        self.p_alignment = get_alig(self.p_alig_val)
        
        # attributes
        self.p_age       = self.smart_age()
        self.p_fname     = ran_gen.rname(self.p_race, "First")
        self.p_lname     = ran_gen.rname(self.p_race, "Last")
        self.p_name      = self.p_fname + " " + self.p_lname
        
        # financial & vanity
        self.p_net_worth = ran_gen.rwealth()
        self.p_wea_desc  = ran_gen.get_wealth_desc(self.p_net_worth)
        self.p_clothing  = ran_gen.rarmor()
        self.p_weapon    = ran_gen.rweapon()
        
        # stats
        self.str = stat_gen()
        self.dex = stat_gen()
        self.con = stat_gen()
        self.wis = stat_gen()
        self.int = stat_gen()
        self.cha = stat_gen()
        
    def logical_stereotype(self, *r):
        """Normalize stereotypical alignment & class based on 5th ed. PHB"""
        if len(r) > 0: self.p_race = r[0]
        if self.p_race != "Human":
            pot_aligns = get_class_ster_nums(self.p_race, "pot_aligns", 1, \
                        True)
            pot_alig_nums = get_class_ster_nums(self.p_race, "pot_alig_nums", \
                        2, True)
            class_nums = get_class_ster_nums(self.p_race, "class_nums", 1, \
                        True)
            pot_classes = get_class_ster_nums(self.p_race, "pot_classes", 1, \
                        False)
            
            ster_align = die.rolld(100)    # sterotypical alignment %
            ster_class = die.rolld(100)    # stereotypical class %
                
            for i in range(len(pot_aligns)):
                if ster_align <= pot_aligns[i]:
                    self.p_alig_val[0] = pot_alig_nums[i][0]
                    self.p_alig_val[1] = pot_alig_nums[i][1]
                    
                    for j in range(len(class_nums)):
                        if ster_class <= class_nums[j]:
                            self.p_class = pot_classes[j]
                            break
                    break
    
        self.p_alignment = get_alig(self.p_alig_val)
            
    def smart_age(self):
        """
            Give character an appropriate age for given race
            Max age taken from 5th edition PHB, or D&D Beyond if not
            listed in PHB
        """
        r = die.rolld(100) # random percentage
        r_a_mod = random.randrange(86, 99) / 100 # age modifier
        
        # the following are ordered according to dnd_world
        maxa = [80, 533, 845, 322, 222, 125, 75, 98, 102] # max ages
        aa = [15, 80, 120, 65, 28, 17, 15, 17, 17] # adulthood ages
        r_a_check = [.74, .88, .85, .85, .70, .82, .88, .79, .82] # age check
        alter_chance = [89, 89, 89, 89, 85, 89, 89, 82, 89] # age alter chance
        
        for i in range(len(World.LINEAGE.value)):
            if self.p_race == World.LINEAGE.value[i]:
                age = die.rolld(maxa[i] - aa[i]) + aa[i]
                if age >= int(r_a_check[i] * maxa[i]) and r < alter_chance[i]:
                    age = int(age * r_a_mod)
                break
        
        return age
   
    def smart_wealth(self):
        "Make a somewhat logical attempt at calculating wealth based on class"
        new_wealth = die.rolld(World.W_THRESH.value[World.CLASS_MONEY.value[self.p_class]-1])
        
        self.p_net_worth = new_wealth
        self.p_wea_desc = ran_gen.get_wealth_desc(new_wealth)

    def smart_stats(self):
        config = ConfigParser()
        ph = [self.str, self.dex, self.con, self.wis, self.int, self.cha]
        ph.sort()
        ph.reverse()
        p = random.randint(1, 100)
        thresh = []
        to_read = ""
        
        for i in range(len(World.CLASSES.value)):
            if self.p_class == World.CLASSES.value[i]:
                to_read = f"configs/config_{World.CL_SH.value[i]}.ini"
                break
            
        config.read(to_read)
        
        for section in config.sections():
            thresh.append(int(section))
        
        for i in range(len(thresh)):
            if p <= thresh[i]:
                for st_name, st_pos in config[str(thresh[i])].items():
                    setattr(self, st_name, ph[int(st_pos)])
                break
            
    def smart_gear(self):
        conf_class = ConfigParser()
        conf_class.read("configs/config_classes.ini")
        conf_armor = ConfigParser()
        conf_weap = ConfigParser()
        
        c = self.p_class
        
        arm_budget = int(self.p_net_worth * .38)
        weap_budget = int(self.p_net_worth * .1)
        shield_budg = int(self.p_net_worth * .08)
        shield_chan = random.randint(1, 100)
        can_wield_shield = conf_class[c]["shield"]
        
        avail_armor = []
        avail_weap = []
        
        if conf_class[c]["ar_kind"].split(",")[0] != '':
            armor_type = random.choice(conf_class[c]["ar_kind"].split(","))
        else:
            armor_type = random.choice(list(World.ARM_TYPE.value.keys()))
            
        conf_armor.read(World.ARM_TYPE.value[armor_type])
        
        for section in conf_armor.sections():
            avail_armor.append(dc(section))
            
        for _ in range(len(avail_armor) + 1):
            if len(avail_armor) == 0 or c == "Wizard":
                armor = "just the clothes on their back"
                break
            
            pick = random.choice(avail_armor)
            price = int(conf_armor[pick]["price"])

            if price <= arm_budget:
                armor = pick
                break
            else:
                avail_armor.remove(pick)
        
        weap_type = random.choice(conf_class[c]["weap_type"].split(","))
        conf_weap.read(World.WEAP_TYPE.value[weap_type])
        
        for sect in conf_weap.sections():
            avail_weap.append(dc(sect))
        
        for _ in range(len(avail_weap) + 1):
            if len(avail_weap) == 0:
                if c == "Cleric":
                    weapon = "only a symbol of their faith"
                else:
                    weapon = "their hands"
                break
            
            pick = random.choice(avail_weap)
            price = float(conf_weap[pick]["price"])
            
            if price <= weap_budget:
                weapon = pick
                break
            else:
                avail_weap.remove(pick)
                
        shield_conditions = [
            can_wield_shield == "True", \
            shield_budg >= 10, \
            shield_chan <= 40, \
            conf_weap[pick]["can_wield_shield"] == "True"]
        
        for cond in shield_conditions:
            if not cond:
                can_wield_shield = False
                break
            
        if can_wield_shield:
            weapon += " and shield"
        
        self.p_clothing = armor
        self.p_weapon = weapon