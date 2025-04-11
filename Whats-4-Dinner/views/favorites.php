<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Favorites</title>
        <script src="views/assets/scripts.js" defer></script>
        <style>
<?php include "assets/theme.css" ?>
        </style>
    </head>
    <body>
        <?php include 'nav_bar.php'; //Nav bar img map

        if (sizeof($favorites) > 0) { //PHP echos h1&h3 headers with user name if user has favs
            echo "<h1 style='padding-top: 7%; margin-top:0px;'>" . $_SESSION['name'] . "'s Favorite Recipes</h1>";
            echo "<h3>Click an image to remove the recipe from your favorites</h3>";
            include 'recipe_list.php';
        } else {
            include 'add_rec.php';  //or else includes add recipes segment
        }
        ?>

    </body>
</html>
<script src="views/assets/jquery-3.6.1.min.js" defer></script>