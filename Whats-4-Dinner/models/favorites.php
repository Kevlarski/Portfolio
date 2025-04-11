<?php
//starts session
session_start();

//returns a multidimensional array of recipe information
function list_favs() {
    //PDO
    global $database;
    
    //$favs will be an array of the unique recipe ids
    $favs = get_favs();
    $favorites = array();
    
    //individual queries for each favorite
    foreach ($favs as $fav) {
        $query = 'SELECT `directions`, `pic`, `name` FROM `recipes` '
                . 'WHERE `recipes`.`recipe_id` = :recipe_id';
        $statement = $database->prepare($query);
        $statement->bindValue(":recipe_id", $fav);
        $statement->execute();
        $recipes_data = $statement->fetchAll(PDO::FETCH_NUM);
        $statement->closeCursor();
        
        //attaches an array of the recipe's ingredient list with measure and amount
        $recipes_data[0][3] = ingredients($fav);
        //adds the interation's data to the overall array
        $favorites[] = $recipes_data;
    }
    return $favorites;
}

//returns an array of unique recipe ids
function get_favs() {
    //PDO
    global $database;

    //Queries for all recipe ids associated with the user
    $query = 'SELECT `recipe_id` FROM `favorited` WHERE `favorited`.`user_id` = :user_id;';
    $statement = $database->prepare($query);
    $statement->bindValue(":user_id", $_SESSION['id']);
    $statement->execute();
    $favs_data = $statement->fetchAll(PDO::FETCH_NUM);
    $statement->closeCursor();

    //consolidates array
    $favs = array();
    foreach ($favs_data as $fav) {
        foreach ($fav as $value) {
            $favs[] = $value;
        }
    }
    //ensures no duplicates
    $favs = array_unique($favs);
    return $favs;
}

//returns array of all ingredient names
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

//delete row from favorited
function remove_favorite($name) {
    $recipe_id = getRecipeId($name); //get id from name
    global $database; //PDO

    $query = "DELETE FROM `favorited` "
            . "WHERE ((`user_id` = :user_id) AND (`recipe_id` = :recipe_id))";
    $statement = $database->prepare($query);
    $statement->bindValue(":recipe_id", $recipe_id[0]);
    $statement->bindValue(":user_id", $_SESSION['id']);
    $statement->execute();
    $statement->closeCursor();
}

//returns  recipe id based on name
function getRecipeId($name) {
    global $database;

    $query = 'SELECT `recipe_id` FROM `recipes` WHERE `recipes`.`name` = :name';
    $statement = $database->prepare($query);
    $statement->bindValue(":name", $name);
    $statement->execute();
    $recipe_id = $statement->fetch(PDO::FETCH_NUM);
    $statement->closeCursor();
    return $recipe_id;
}

//lists all recipes
function list_all() {
    global $database;
    
    $query = 'SELECT `directions`, `pic`, `name` FROM `recipes`';
    $statement = $database->prepare($query);
    $statement->execute();
    $recipes_data = $statement->fetchAll(PDO::FETCH_NUM);
    $statement->closeCursor();

    $i = 0;
    //attaches ingredient list
    while ($i < sizeof($recipes_data)) {
        $recipes_data[$i][3] = ingredients($i+1);
        $i++;
    }
    return $recipes_data;
}
