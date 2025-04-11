<?php

//start session
session_start();

//returns recipe ingredient info based on recipe id
function ingredients($recipe_id) {
    global $database; //PDO
    //get the measure and amount from recipe ingredients and name from ingredients
    //based on the recipe id and ingredient id
    $query = "SELECT `recipe_ingredient`.`amount`, `recipe_ingredient`.`measure`, `ingredients`.`name`"
            . " FROM `ingredients` , `recipe_ingredient` WHERE (( `recipe_id` = :recipe_id) "
            . "AND ( `ingredients`.`ingredient_id` = `recipe_ingredient`.`ingredient_id`))";

    $statement = $database->prepare($query);
    $statement->bindValue(":recipe_id", $recipe_id);
    $statement->execute();
    $recipes = $statement->fetchAll(PDO::FETCH_NUM);
    $statement->closeCursor();

    return $recipes;
}

//returns an array of all current users pantry items
function get_pantry_names() {
    $ingredient_list = array();
    foreach ($_SESSION['pantry'] as $ingredient) {
        $ingredient_list[] = $ingredient[0];
    }
    return $ingredient_list;
}

//returns an array of recipe info based on recipe id
function list_all_menu($recipe_id) {
    global $database;

    $query = 'SELECT `directions`, `pic`, `name`, `recipe_id`  
        FROM `recipes` WHERE `recipe_id` = :id';
    $statement = $database->prepare($query);
    $statement->bindValue(":id", $recipe_id);
    $statement->execute();
    $recipes_data = $statement->fetchAll(PDO::FETCH_NUM);
    $statement->closeCursor();

    $i = 0;
    //attaches ingredient info
    while ($i < sizeof($recipes_data)) {
        $id = $recipes_data[$i][3];
        $recipes_data[$i][3] = ingredients($id);
        $i++;
    }
    return $recipes_data;
}

//returns an array of ingredient names
function get_ingredient_name($ingredients) {
    $ingredient_list = array();
    foreach ($ingredients as $ingredient) {
        $ingredient_list[] = $ingredient[2];
    }
    return $ingredient_list;
}

//returns 
function get_recipe_ingredients() {

    //gets array in form {'recipe_name': #}
    $recipe_ingredient_list = strip_and_flip(get_recipe_names());

    foreach ($recipe_ingredient_list as $recipe => $ingredient) {
        if ($ingredient == 0) {
            //the first item is a spacer, skip it
            continue;
        }

        //attaches ingredient list to array {'recipe name': array{#: 'ingredient name'}}
        $ingredient_names = get_ingredient_name(ingredients($ingredient));
        $recipe_ingredient_list[$recipe] = $ingredient_names;
    }
    
    //removes the spacer before returning
    $recipe_list = array_slice($recipe_ingredient_list, 1, sizeof($recipe_ingredient_list) - 1, true);

    return $recipe_list;
}

//returns an array of all recipe names
function get_recipe_names() {
    global $database;

    $query = "SELECT `name` FROM `recipes`";

    $statement = $database->prepare($query);
    $statement->execute();
    $recipes = $statement->fetchAll(PDO::FETCH_NUM);
    $statement->closeCursor();

    return $recipes;
}

//returns an array containing instances of 
function strip_and_flip($recipes) {
    //add spacer so recipe ids match up
    $ingredient_list = ["This space intentionally left blank"];

    foreach ($recipes as $recipe) {
        $ingredient_list[] = $recipe[0];
    }

    //changes from {# : 'recipe name'} to {'recipe_name': #}
    $recipe_ingredient_list = array_flip($ingredient_list);

    return $recipe_ingredient_list;
}

//return recipe id based on name
function get_recipe_id($name) {
    global $database;

    $query = "SELECT `recipe_id` FROM `recipes` WHERE `name` = :name";

    $statement = $database->prepare($query);
    $statement->bindValue(":name", $name);
    $statement->execute();
    $recipes_result = $statement->fetch();
    $statement->closeCursor();
    $recipe_id = $recipes_result;
    return $recipe_id;
}

//adds a clicked recipe to favorites
function add_fav($name) {
    global $database; //PDO
    
    $recipe_id = get_recipe_id($name)[0]; //id from name

    //checks to see if the recipe already exists in the table
    $query = "SELECT `id` FROM `favorited` WHERE ((`favorited`.`user_id`=:user_id) AND (`favorited`.`recipe_id`=:recipe_id));";
    $statement = $database->prepare($query);
    $statement->bindValue(":user_id", $_SESSION['id']);
    $statement->bindValue(":recipe_id", $recipe_id);
    $statement->execute();
    $results = $statement->fetchAll();

    //if not, INSERT
    if (sizeof($results) < 1) {
        $query = "INSERT INTO `favorited` (`user_id`, `recipe_id`) "
                . "VALUES (:user_id, :recipe_id);";
        $statement = $database->prepare($query);
        $statement->bindValue(":user_id", $_SESSION['id']);
        $statement->bindValue(":recipe_id", $recipe_id);
        $statement->execute();
        
        
    }
    $statement->closeCursor();
}
