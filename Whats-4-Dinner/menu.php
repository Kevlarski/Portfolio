<?php

//starts session
session_start();
try {
    //makes sure this controller has access to the database,
    //the menu model, and the pantry model
    require_once 'models/database.php';
    require_once 'models/menu.php';
    require_once 'models/pantry.php';

    //$id is the currently logged in user's id
    //$action is the name of the recipe
    $id = $_SESSION['id'];
    $action = filter_input(INPUT_POST, "action");

    //login validation, redirects to index.php if not logged in
    $logged_in = $_SESSION['is_logged_in'];
    if ($logged_in) {
        $_COOKIE['is_logged_in'] = $logged_in;
    } else {
        header('Location: index.php');
    }

    //adds clicked image's recipe to favorited
    if (isset($action)) {
        add_fav($action);
    }

    //$recipes will have the format {"recipe_name": array("ingredient names")}
    $recipes = get_recipe_ingredients();

    //array of all current user's pantry items
    $pantry_ingredients = get_pantry_names();
    //empty arrays to hold formatted results
    $perfect = array();
    $close = array();


    foreach ($recipes as $recipe_name => $ingredients) {

        //getting array sizes for comparison
        $pantry_size = sizeof($pantry_ingredients);
        $ingredients_size = sizeof($ingredients);

        //compare each pantry against the iteration's ingredient list
        //$intersect_size is how many ingredients the user has in the recipe
        $intersect_size = sizeof(array_intersect($pantry_ingredients, $ingredients));

        //same size lists means it's a perfect match
        if ($intersect_size == $ingredients_size) {
            $perfect[] = list_all_menu(get_recipe_id($recipe_name)[0]);
        } 
        //otherwise, if the intersect array is missing 3 or less ingredients,
        //or if the size of the intersect list is more than 4 it's close
        else if (($ingredients_size - $intersect_size) < 4 || ($intersect_size > 5)) {
            $close[] = list_all_menu(get_recipe_id($recipe_name)[0])[0];
        }
    }//end foreach
    
    //When an image is clicked it POSTs the recipe name to $action
    if ($action != "") {
        add_fav($action);
    }

    //HTML view
    include('views/menu.php');
    
    if($action!=''){
        //JavaScript alert box to announce which recipe was added
        echo "<script defer>window.alert('Added \"$action\" to Favorites!');</script>";
    }
} catch (Exception $ex) {
    $error_message = $ex->getMessage();
    include('views/error.php');
}