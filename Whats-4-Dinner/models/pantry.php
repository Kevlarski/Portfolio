<?php
//starts session
session_start();

//globally accessable array
$pantry_list = array();

//returns names of all items in pantry
function get_pantry($id) {
    global $database; //PDO
    global $pantry_list;
    $query = 'SELECT name FROM pantry WHERE user_id=:id';
    $statement = $database->prepare($query);
    $statement->bindValue(":id", $id);
    $statement->execute();
    $pantry_items = $statement->fetchAll(PDO::FETCH_NUM);
    $statement->closeCursor();

    //adds result list to global array
    $pantry_list = array_merge($pantry_items, $pantry_list);
    $_SESSION['pantry'] = $pantry_items; //add items to session
    return $pantry_items;
}

//returns an array of all ingredients in ingredients table
function list_ingredients() {
    global $database; //PDO
    global $pantry_list;
    $query = 'SELECT name FROM ingredients';
    $statement = $database->prepare($query);
    $statement->execute();
    $ingredients_full = $statement->fetchAll(PDO::FETCH_NUM);
    $statement->closeCursor();

    //consolidates the return format
    $ingredients = array();
    foreach ($ingredients_full as $ingredient) {
        if (!in_array($ingredient, $pantry_list)) {
            $ingredients[] = $ingredient;
        }
    }
    return $ingredients;
}

//INSERTs added ingredients to pantry table
function addPantry($ingredient) {
    global $database; //PDO
    //gets the ingredient id by name and user id
    $ingredient_id = getIngredientId($ingredient);
    $user_id = $_SESSION['id'];

    $query = "INSERT INTO pantry (name, ingredient_id, user_id) VALUES (:name, :ingredient_id,:user_id)";
    $statement = $database->prepare($query);
    $statement->bindValue(":name", $ingredient);
    $statement->bindValue(":ingredient_id", $ingredient_id[0]);
    $statement->bindValue(":user_id", $user_id);
    $statement->execute();
    $statement->closeCursor();
}

//returns ingredient id
function getIngredientId($ingredient) {
    //PDO
    global $database;

    //gets ingredient id based on name
    $query = 'SELECT `ingredient_id` FROM `ingredients` WHERE `ingredients`.`name` = :name';
    $statement = $database->prepare($query);
    $statement->bindValue(":name", $ingredient);
    $statement->execute();
    $ingredient_id = $statement->fetch(PDO::FETCH_NUM);
    $statement->closeCursor();

    return $ingredient_id;
}

//removes ingredients from user's pantry
function removeIngredient($ingredient) {
    //PDO
    global $database;

    //Gets ingredient id based on it's name
    $ing_id = getIngredientId($ingredient);

    //delets row from pantry table based on user id and recipe id
    $query = 'DELETE FROM `pantry` WHERE `pantry`.`user_id` = :user AND `pantry`.`ingredient_id` = :ingredient';
    $statement = $database->prepare($query);
    $statement->bindValue(":user", $_SESSION['id']);
    $statement->bindValue(":ingredient", $ing_id[0]);
    $statement->execute();
    $statement->closeCursor();
}

//logs current user out
function logout() {

    //checks for logged in cookie, sets session login to false
    if ($_COOKIE['is_logged_in']) {
        $_SESSION['is_logged_in'] = false;
    }
}
