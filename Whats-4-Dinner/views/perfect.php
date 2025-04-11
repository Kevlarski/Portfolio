<!-- Perfect matches segment-->

<h1 style="padding-top: 7%; margin-top:0px;">You've got everything for this!</h1>
<h3>Click an image to add the recipe to your favorites</h3>
<table id="favs">
    <?php foreach ($perfect as $perfect) : ?> 
        <tr class="favs">
            <td  class="favs" id="recipe_name"><?php echo $perfect[0][2]; ?></td>
            <td  class="favs" style="text-align: center; padding: 5px;">
                <?php
                $server = $_SERVER['REQUEST_URI'];
                echo "<form action='$server' method='post'>";
                echo $perfect[0][1];
                ?></td>
            <td class="favs" rowspan="2" style=" padding: 5px; background-color: rgba(120,120,120,.25);"><?php echo $perfect[0][0]; ?></td>
        </tr>
        <tr class="favs" style="padding: 5px;">
            <td class="favs" colspan="2" style="padding: 5px; background-color: rgba(120,120,120,.25);">
                <ul>
                        <?php foreach ($perfect[0][3] as $ingredient_list) : ?>
                        <li style='font-family: arial;'>
                            <?php foreach ($ingredient_list as $ingredient_item) : ?>
                            <?php echo $ingredient_item . " "; ?>
                        <?php endforeach; ?>
                        </li>
    <?php endforeach; ?>
                </ul>
            </td>
        </tr>
<?php endforeach; ?>
</table>