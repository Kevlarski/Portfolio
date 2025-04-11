<?php
try {
    //access to the database and favorite models
    require_once 'models/database.php';
    require_once 'models/favorites.php';

    //lists all available recipes
    $favorites = list_all();
    
    //HTML view
    include('views/browse.php');
} catch (Exception $ex) {
    $error_message = $ex->getMessage();
    include('views/error.php');
}

