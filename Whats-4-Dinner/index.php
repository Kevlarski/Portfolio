<?php

//starts session
session_start();
try {
    //makes sure this controller has access to the database and the index model
    require_once 'models/database.php';
    require_once 'models/index.php';

    //varibles from forms
    $password = htmlspecialchars(filter_input(INPUT_POST, "password"));
    $password_hash = password_hash($password, PASSWORD_DEFAULT);
    $email = htmlspecialchars(filter_input(INPUT_POST, "email"));
    $register_email = htmlspecialchars(filter_input(INPUT_POST, "register_email"));
    $name = htmlspecialchars(filter_input(INPUT_POST, "name"));
    $action = htmlspecialchars(filter_input(INPUT_POST, "action"));
    $message = "";

    //log out
    if ($action == "logout") {
        $_SESSION = array();
        session_destroy();
    }

    //if the form is filled and register is clicked
    if ($action == "add" && $name != "" && $register_email != "" && $password != "") {
        //make a user object with info
        $user = new user($name, $register_email, $password, $password_hash);
        insert($user);
        header("Location:pantry.php");
        $_SESSION['is_logged_in'] = true;
        $_SESSION['name'] = explode(" ", sessionUserName($register_email))[0];
        $_SESSION['id'] = sessionUserID($register_email);
    }
    //else if the 
    else if ($action == "login" && $email != "") {
        if (login($email, $password)) {
            header("Location:pantry.php");
            $_SESSION['is_logged_in'] = true;
            $_SESSION['name'] = sessionUserName($email);
            $_SESSION['id'] = sessionUserID($email);
        } else {
            $message = "Email address or password incorrect, please try again";
            echo "<script>alert('$message');</script>";
        }
    }

    //log in redirects to user's pantry
    if ($_SESSION['is_logged_in']) {
        header("Location:pantry.php");
    }

    //HTML view
    include('views/index.php');
} catch (Exception $ex) {
    $message = $ex->getMessage();
    include('views/error.php');
}


