<?php

//starts session
session_start();
try {
    //ensures access to database and favorites models
    require_once 'models/database.php';
    require_once 'models/favorites.php';

    //$action is the name of the recipe clicked
    $action = htmlspecialchars(filter_input(INPUT_POST, "action"));
    if ($action != '') {
        remove_favorite($action);
        //JavaScript confirmation message
        echo "<script defer>window.alert('Removed \"$action\" from Favorites!');</script>";
    }

    //login validation
    $logged_in = $_SESSION['is_logged_in'];
    if ($logged_in) {
        $_COOKIE['is_logged_in'] = $logged_in;
    } else {
        header('Location: index.php');
    }

    //list all logged in user's favorite recipes
    $favorites = list_favs();

    //HTML view
    include('views/favorites.php');
} catch (Exception $ex) {
    $error_message = $ex->getMessage();
    include('views/error.php');
}

