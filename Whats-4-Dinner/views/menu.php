<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Menu</title>
        <script src="views/assets/scripts.js" defer></script>
        <script src="views/assets/jquery-3.6.1.min.js" defer></script>
        <style>
<?php include "assets/theme.css" ?>
        </style>
    </head>
    <body>
        <?php include 'nav_bar.php'; ?><!--Nav bar img map-->

        <?php
        if (sizeof($perfect) > 0) {  //checks for perfect matches
            include 'perfect.php';   //to include the perfect segment
        }
        if (sizeof($close) > 0) {    //checks for close matches
            include 'close.php';     //to include the close segment
        }
        if (sizeof($perfect)< 1&&sizeof($close) < 1){
            include 'add_more.php';  //if no close or perfect matches
        }                            //display add more segment
        ?>
    </body>
</html>
