#!/usr/bin/python

# imports
from models.dnd_char import character
from configs.dnd_world import World
#from textwrap import wrap
from time import sleep
import json
import models.prettify as pretty

def menu():
    "Display menu options"
    print("-----------------------------------")
    print(" 1| Generate New Logical Character")
    print(" 2| Generate New Random Character")
    print(" 3| Generate Multiple Random Characters")
    print("-----------------------------------")
    print(" 4| Generate Character of Certain Class")
    print(" 5| Generate Character Of Certain Race")
    print(" 6| Generate Character Of Certain Race and Class")
    print("-----------------------------------")
    print(" 7| Edit Current Character")
    print(" 8| Print Stats of Current Character")
    print(" 9| Save Character To File")
    print("-----------------------------------")
    print(" 0| Display Menu\n")
    
def save_char(c, char_made, has_saved):
    "Save character to text file"
    
    if char_made == False:
        pretty.alert("ERROR: Generate character before saving.")
        menu()
    elif has_saved == True:
        pretty.alert("ERROR: Character has already been saved.")
        menu()
        return True
    else: 
        file_name = f"{c.p_race} {c.p_class} - {c.p_name}.txt"
        savepath  = "./characters/" + file_name
        current   = "./current_character.txt"
        files     = [savepath, current]
        
        for a_file in files:
            file = open(a_file, 'w')
            file.write(pretty.print_desc(c),)
            file.close()
        
        pretty.alert(f"'{file_name}' saved in characters folder")
        
        return True
    
def json_save(c, char_made, has_saved):
    if char_made == False:
        pretty.alert("ERROR: Generate character before saving.")
        menu()
    elif has_saved == True:
        pretty.alert("ERROR: Character has already been saved.")
        menu()
        return True
    else: 
        file_name = f"{c.p_race} {c.p_class} - {c.p_name}.json"
        savepath  = "./characters/" + file_name
        file = open(savepath, 'w')
        file.write(json.loads(c))
        file.close()
        
        pretty.alert(f"'{file_name}' saved in characters folder")
        
        return True

def make_log_character(*r):
    "Generate character based on 5e PHB logic"
    new = character()
    if len(r) > 0: new.logical_stereotype(r[0])
    else: new.logical_stereotype()
    new.smart_stats()
    new.smart_wealth()
    new.smart_gear()
    
    print("- - - - LOGICAL CHARACTER - - - -".center(80, ' '))
    print(f"{pretty.print_desc(new)}\n")
    
    return new

def make_log_class(*r):
    "Generate character based on selected class"
    new = character()
    new.p_class = r[0]
    new.smart_stats()
    new.smart_wealth()
    new.smart_gear()
    big_class = str(new.p_class)
    print(f"- - - - LOGICAL {big_class.upper()} CHARACTER- - - -".center(80, ' '))
    print(f"{pretty.print_desc(new)}\n")
    
def make_ran_character():
    "Generate character randomly"
    new = character()
    print("- - - - RANDOM CHARACTER - - - -".center(80, ' '))
    print(f"{pretty.print_desc(new)}\n")
    return new

def list_saves(save_path):
    pass

def multi_char(num,save=0):
    i = 0
    if save == 1:
        file_name = f"multi-char({num}).txt"
        savepath  = "./characters/" + file_name
        file=open(savepath, 'a')
        while i < num:
            new = character()
            print(f"- - - - RANDOM CHARACTER {i+1}- - - -".center(80, ' '))
            print(f"{pretty.print_desc(new)}\n")
            file.write(pretty.print_desc(new),)
            i=i+1
        file.close()
        pretty.alert(f"'{file_name}' saved in characters folder")
    else:
        while i < num:
            new = character()
            print(f"- - - - RANDOM CHARACTER {i+1}- - - -".center(80, ' '))
            print(f"{pretty.print_desc(new)}\n")
            i=i+1
    
    return True

def menu_handler():
    curr = character()
    has_saved = False
    char_made = False
    debug_count = 500
    cont = 1

    while cont >= 0:
        cont = pretty.prompt("Enter a number (0 for menu): ")
        pretty.clear()
        
        if cont == 1:
            "Make logical char, display stats, and reset saved stat"
            curr = make_log_character()
            has_saved = False
            char_made = True
            
        elif cont == 2: 
            "Make random char, display stats, and reset saved stat"
            curr = make_ran_character()
            has_saved = False
            char_made = True
            
        elif cont == 3:
            "Generate a user determined number of random characters"
            char_num = pretty.prompt("How many characters would you like to generate? ")
            pretty.clear()
            save = pretty.prompt("Would you like to save? \n1) Yes\n2) No\n")
            if save == 1:
                multi_char(char_num,save)
            else:
                multi_char(char_num,0)
        
        elif cont ==4:
            "Make char with a given class"
            pretty.display_classes()
            selected_class = pretty.prompt("Choose a class: ")
            if selected_class == 0:
                pretty.clear()
                pass
            else:
                pretty.clear()
                curr = make_log_class(World.CLASSES.value[selected_class-1])
            has_saved = False
            char_made = True
        
        elif cont == 5: 
            "Make char with a given race"
            pretty.display_races()
            selected_race = pretty.prompt("Choose a race: ")
            if selected_race == 0:
                pretty.clear()
                pass
            else:
                pretty.clear()
                curr = make_log_character(World.LINEAGE.value[selected_race-1])
            has_saved = False
            char_made = True
        
        elif cont ==6:
            "Make char with given race and class"
            #has_saved = json_save(curr, char_made, has_saved)
            print("Not Ready Yet")
            
        elif cont ==7:
            "Edit current character"
            print("Not Ready Yet")
            
        elif cont == 9: 
            "Display errors w/ saving"
            has_saved = save_char(curr, char_made, has_saved)
            
        elif cont == 8:
            "Print stats if character has been generated"
            if char_made == False:
                pretty.alert("ERROR: Generate character before attempting to view their stats.")
                menu()
            else:
                print(f"{pretty.print_desc(curr)}\n")
                
        elif cont == 111:
            for i in range(debug_count):
                print(f"DEBUG MODE (LOG) ({i} of {debug_count})".center(80, '-'))
                curr = make_log_character()
                sleep(.01)
                pretty.clear()
            has_saved = False
            char_made = True
            pretty.alert("CHAR GEN SUCCESSFUL - NO BUGS")
            menu()
                
        elif cont == 222:
            for i in range(debug_count):
                print(f"DEBUG MODE (RAN) ({i} of {debug_count})".center(80, '-'))
                curr = make_ran_character()
                sleep(.01)
                pretty.clear()
            has_saved = False
            char_made = True
            pretty.alert("CHAR GEN SUCCESSFUL - NO BUGS")
            menu()
            
        elif cont == 0:
            menu()

        else:
            quit_cont = input("Would you like to quit?\n")+" "
            if str.lower(quit_cont[0]) != "n":
                cont = 0
                break
            else:
                pretty.clear()
                cont = 0
                menu()
