<!--Display recipes for the Browse page-->

<table id="favs">
    <?php foreach ($favorites as $favorite): ?> 
        <tr class="favs">
            <td class="favs" id="recipe_name"><?php echo $favorite[2]; ?></td>
            <td class="favs" style="text-align: center; padding: 5px;">
                <?php
                $server = $_SERVER['REQUEST_URI'];
                echo "<form action='$server' method='post'>"
                ?>
                <?php echo $favorite[1]; ?>
            </td>
            <td class="favs" rowspan="2" style=" padding: 5px; background-color: rgba(120,120,120,.25);"><?php echo $favorite[0]; ?></td>
        </tr>
        <tr class="favs" style="padding: 5px;">
            <td class="favs" colspan="2" style="padding: 5px; background-color: rgba(120,120,120,.25);">
                <ul>
                    <?php foreach ($favorite[3] as $ingredient_list): ?>
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
