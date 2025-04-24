#!/usr/bin/python

# imports
import main as m

import dnd_world as World

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


def menu_handler():
    cont =1
    while cont >= 0:
        cont = prompt("Enter a number (0 for menu): ")
        clear()
        
        if cont == 1:
            "Make logical char, display stats, and reset saved stat"
            curr = m.make_log_character()
            has_saved = False
            char_made = True
            
        elif cont == 2: 
            "Make random char, display stats, and reset saved stat"
            curr = m.make_ran_character()
            has_saved = False
            char_made = True
            
        elif cont == 3:
            "Generate a user determined number of random characters"
            char_num = prompt("How many characters would you like to generate? ")
            m.clear()
            m.multi_char(char_num)
        
        elif cont ==4:
            "Make char with a given class"
            m.display_classes()
            selected_class = prompt("Choose a class: ")
            if selected_class == 0:
                clear()
                pass
            else:
                clear()
                curr = m.make_log_class(World.CLASSES.value[selected_class-1])
            has_saved = False
            char_made = True
        
        elif cont == 5: 
            "Make char with a given race"
            m.display_races()
            selected_race = m.prompt("Choose a race: ")
            if selected_race == 0:
                clear()
                pass
            else:
                clear()
                curr = m.make_log_character(World.RACES.value[selected_race-1])
            has_saved = False
            char_made = True
        
        elif cont ==6:
            "Make char with given race and class"
            has_saved = m.jsonSave(curr, char_made, has_saved)
            
        elif cont ==7:
            "Edit current character"
            print("Not Ready Yet")
            
        elif cont == 9: 
            "Display errors w/ saving"
            has_saved = m.save_char(curr, char_made, has_saved)
            
        elif cont == 8:
            "Print stats if character has been generated"
            if char_made == False:
                m.alert("ERROR: Generate character before attempting to view their stats.")
                menu()
            else:
                print(f"{m.print_desc(curr)}\n")
                
        elif cont == 111:
            for i in range(m.debug_count):
                print(f"DEBUG MODE (LOG) ({i} of {m.debug_count})".center(80, '-'))
                curr = m.make_log_character()
                m.sleep(.01)
                clear()
            has_saved = False
            char_made = True
            m.alert("CHAR GEN SUCCESSFUL - NO BUGS")
            menu()
                
        elif cont == 222:
            for i in range(m.debug_count):
                print(f"DEBUG MODE (RAN) ({i} of {m.debug_count})".center(80, '-'))
                curr = m.make_ran_character()
                m.sleep(.01)
                clear()
            has_saved = False
            char_made = True
            m.alert("CHAR GEN SUCCESSFUL - NO BUGS")
            menu()
            
        elif cont == 0:
            menu()

        else:
            quit_cont = input("Would you like to quit?\n")+" "
            if str.lower(quit_cont[0]) != "n":
                cont = 0
                break
            else:
                clear()
                cont = 0
                menu()