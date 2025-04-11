<?php
//starts session
session_start();
try {
    //makes sure this controller has access to the database and the pantry model
    require_once 'models/database.php';
    require_once 'models/pantry.php';

    //$action is the source of the POST 
    //$ingredient is the name of the selected ingredient
    $action = htmlspecialchars(filter_input(INPUT_POST, "action"));
    $ingredient = htmlspecialchars(filter_input(INPUT_POST, "ingredient"));

    //login validation, redirects to index.php if not logged in
    $logged_in = $_SESSION['is_logged_in'];
    if ($logged_in) {
        $_COOKIE['is_logged_in'] = $logged_in;
    } else {
        header('Location: index.php');
    }

    if ($action == "add") {
        // the first option listed in the select form is not an ingredient
        if ($ingredient != "none") {
            addPantry($ingredient);
        }
    } else if ($action != "add") {
        //remove buttons return ingredient name instead of add
        removeIngredient($action);
    }


    //Lists the logged in user's pantry items
    $pantry_items = get_pantry($_SESSION['id']);
    //Lists all ingredients in all recipes
    $ingredients = list_ingredients();

    //HTML view 
    include('views/pantry.php');
    
} catch (Exception $ex) {
    $error_message = $ex->getMessage();
    include('views/error.php');
}