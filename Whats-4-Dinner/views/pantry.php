<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pantry</title>
        <script src="views/assets/scripts.js" defer></script>
        <script src="views/assets/jquery-3.6.1.min.js" defer></script>
        <style>
<?php include "assets/theme.css" ?>
        </style>    
    </head>
    <body style='font-family: avoid;'>

        <?php include 'nav_bar.php'; ?><!--The nav bar img map -->
        <h1 style="padding-top: 7%; margin-top:0px;">Add ingredients you currently have</h1>
        <h3>then head over to the menu page to see what you can make!</h3>
        <!--Ingredient list-->
        <div id='holder'>
            <form action='pantry.php' method="post">
                <input type="hidden" name="action" value="add">
                <div id='addIngredient'>
                    <p id="addButton">Add</p>
                    <input type="image" src="views/assets/adding.png" alt='add' title="Add Ingredient" style="float:right; margin-left: 20%;">
                </div>
                <div class="custom-select" style="width:200px; font-size: large;">
                    <select name='ingredient'>
                        <option value='none'>Ingredients</option>
                        <?php foreach ($ingredients as $ingredient) : ?>
                            <option value="<?php echo $ingredient[0]; ?>" label="<?php echo $ingredient[0]; ?>">
                                <?php echo $ingredient[0]; ?>
                            </option>
                        <?php endforeach; ?>
                    </select>
                </div>
            </form>
        </div>
        <!--User pantry list -->
        <table style='border: unset; display:inline-grid;'>
            <?php foreach ($pantry_items as $item) : ?> 
                <tr style='border: unset;'>
                    <td class='pantry_items' style="text-align: center; font-family: avoid, arial; width:200px; border-radius: 5px; font-size: x-large; letter-spacing: .1em;"><?php echo $item[0]; ?></td>
                    <td class ="deleteBtn" style=' border-radius: 5px;'>
                        <form action='pantry.php' method="post" class='lists' style='margin: 0%;' name="remove">
                            <input type="hidden" name="action" value="<?php echo $item[0]; ?>">
                            <input type="image" name='<?php echo $item[0]; ?>' src="views/assets/deleting.png" title='Remove Ingredient' alt="remove" >
                        </form>
                    </td>
                </tr>
            <?php endforeach; ?>
        </table>
        <!--Button to menu page -->
        <a href="javascript:menu()"><img src="views/assets/tomenubutton.png" style="width: 100px; float:right; margin-right:25%; margin-left: 0px; display: inline-grid" alt="Menu Button"/></a>
    </body>
</html>









