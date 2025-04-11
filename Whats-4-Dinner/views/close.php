<!--Close matches segment-->

<h1 style="margin-top:20px;">You've got most of this!</h1>
<h3>Click an image to add the recipe to your favorites</h3>
<table id="favs">
    <?php foreach ($close as $close) : ?> 
        <tr class="favs">
            <td  class="favs" id="recipe_name"><?php echo $close[2]; ?></td>
            <td  class="favs" style="text-align: center; padding: 5px;">
                <?php
                $server = $_SERVER['REQUEST_URI'];
                echo "<form action='$server' method='post'>";
                echo $close[1];
                ?></td>
            <td class="favs" rowspan="2" style=" padding: 5px; background-color: rgba(120,120,120,.25);"><?php echo $close[0]; ?></td>
        </tr>
        <tr class="favs" style="padding: 5px;">
            <td class="favs" colspan="2" style="padding: 5px; background-color: rgba(120,120,120,.25);">
                <ul>
                        <?php foreach ($close[3] as $ingredient_list) : ?>
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