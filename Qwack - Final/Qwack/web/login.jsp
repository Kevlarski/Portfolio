<%-- 
    Document   : login
    Created on : Nov. 22, 2022, 10:42:31 p.m.
    Author     : Benji Bettagi, Kevin McLaughlin
--%>

<%@page import="qwack.Database"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="qwack.Post" %>
<%@page import="qwack.Profile" %>
<%@page import="qwack.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="views/assets/theme.css"/>
        <title>Qwack - Login</title>
        <style>
            body{
                justify-items: center;
                justify-content: start;
            }

        </style>
    </head>
    <body>
        <div style="padding-left:200%;"><img src="views/assets/logo.png" alt="logo" height="120px" width="auto" /></div>
        <div id="login" style="padding-left: 25%;">
            <p>Log in</p>
            <form action="Qwack" method="post">
                <label>&emsp;Email: </label> 
                <input type="text" name="email"/><br/><br/>
                <label>&emsp;Password: </label> 
                <input type="password" name="password"/>
                <label>&emsp;</label><br/><br/>
                <input type="hidden" name="action" value="login">
                <input type="submit" value="Login"/><br/>
                <br/><br/>
            </form>
            <hr>
            <p>Register</p>
            <form action="Qwack" method="post">
                <label>&emsp;Name: </label> 
                <input type="text" name="name"/><br/><br/>
                <label>&emsp;Email: </label> 
                <input type="text" name="email_register"/><br/><br/>
                <label>&emsp;Password: </label> 
                <input type="text" name="password_register"/>
                <label>&emsp;</label><br/>
                <input type="hidden" name="action" value="add" ><br/>
                &emsp;<input type="submit" value="Register"/> <br/><br/>
            </form>
        </div>
        <div id="global" width="100%">
            <section class="feed" style="float: right; width:100%;">
                <h1>Global Feed</h1>
                <hr>
                <c:forEach items="${postList}" var="i">
                    <div class="post" style="width:300%;">
                        <div class="profile img">
                            <img src="${i.getUser().getPic()}" alt="logo" height="45px" width="auto" alt="Profile picture">
                        </div>
                        <div class="post-body">
                            
                                <span class="username">${i.getUser().getName()}&emsp;
                                    <div style="font-size: 14px; color: gray">${i.getTimestamp()}</div>
                                </span>   
                            
                            <p>${i.getCaption()}</p>
                            <div class="img">
                                <c:if test = "${i.getPic() != null}">
                                    <img src="${i.getPic()}" alt="postPic" height="100px" width="auto"/>
                                </c:if>
                            </div>
                            <div>
                                <form method="post" action="Home"  >
                                    <span  style="font-size: 25px; color: #40ff99"> <img src='views/assets/Like.png' style="background-color: #242444" height="25px" width="auto" title="Like"> ${i.getLikes()}&emsp;</span>    
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </section>
        </div>
    </body>
</html>
