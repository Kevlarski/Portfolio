<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>

        <style>
            <?php include "assets/theme.css" ?>//this is the link to the stylesheet(theme.css in /views/assets)
        </style>
    </head>
    <body>
        <!--Header-->
        <div style="margin-left:35%; line-height:111px">
            <h1 style="display:inline; line-height: 50px;">What's</h1>
            <img style="display:inline; margin-top: 5%; line-height: 0px; width: 90px; height:auto; background-color: rgba(72, 84, 62, .75); box-shadow: 2px 2px 4px #48543e; border-radius: 25px;" src="views/assets/LEAFDUDE.png" alt=""/>
            <h1 style="display:inline;">&nbsp;Dinner</h1>
        </div>
        
        <!-- Browse Button -->
        <div id="container">
            <div id="logo" title="Browse" style="box-shadow: 2px 2px 4px #48543e;">
                <a href="browse.php" class="index" style='width: auto;'>
                    <h1 style="text-shadow: 1.5px 1.5px 4px black;">Browse</h1>
                    <img src="views/assets/LEAFDUDE.png" alt="Leaf Dude" height="250" width="250"/>
                </a>
            </div>
            
            <!-- Login/Register Box (login) -->
            <div id="login">
                <p style='letter-spacing: .2em; font-size: larger;'>Log in</p>
                <form action="index.php" method="post">
                    <label style='letter-spacing: .2em;'>&emsp;Email: </label> 
                    <input type="text" name="email"/><br/><br/>
                    <label style='letter-spacing: .2em; '>&emsp;Password: </label> 
                    <input type="password" name="password"/>
                    <label>&emsp;</label><br/><br/>
                    <input type="hidden" name="action" value="login">
                    <input type="submit" value="Login" style='font-family: avoid; font-weight: bolder; letter-spacing: .35em; '/>&emsp;
                    
                    <br/><br/>
                </form>
                <hr>
                <!-- Login/Register Box (Register) -->
                <p style='letter-spacing: .2em; font-size: larger'>Register</p>
                <form action="index.php" method="post">
                    <label style='letter-spacing: .2em;'>&emsp;Name: </label> 
                    <input type="text" name="name"/><br/><br/>
                    <label style='letter-spacing: .2em;'>&emsp;Email: </label> 
                    <input type="text" name="register_email"/><br/><br/>
                    <label style='letter-spacing: .2em;'>&emsp;Password: </label> 
                    <input type="text" name="password"/>
                    <label style='letter-spacing: .2em;'>&emsp;</label><br/>
                    <input type="hidden" name="action" value="add" ><br/>
                    &emsp;<input style='font-family: avoid; letter-spacing: .2em; font-weight: bold;' type="submit" value="Register"/> <br/><br/>
                </form>
            </div>
        </div>
        <footer><br/></footer>
    </body>
</html>
