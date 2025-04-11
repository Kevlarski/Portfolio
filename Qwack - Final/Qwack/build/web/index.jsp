<%-- 
    Document   : index.jsp
    Created on : Nov. 22, 2022, 10:26:22 p.m.
    Author     : Benji Bettagi, Kevin McLaughlin
--%>

<%@page import="qwack.Database"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="qwack.Post" %>
<%@page import="qwack.Profile" %>
<%@page import="qwack.User" %>


<!doctype html>
<html>
    <head>
        <link rel="stylesheet" href="views/assets/theme.css"/>
        <title>Qwack - Home</title>
        <style>

        </style>
    </head>
    <body>
        <header>
            <a href="Qwack" title="Log Out">
                <div class="logo">
                    <img src="views/assets/logo.png" alt="logo" height="100px" width="auto"/>
                </div>
            </a>
            <nav>
                <ul>
                    <li><a href="GlobalFeed">Global</a></li>
                    <li><a href="Home">Home</a></li>
                </ul>
            </nav>
            <form action="Users" method="post">
                <input type="text" name="searchBar" style="background-color: #242444" placeholder="Search">
            </form>
            <!-- This is the user info -->
            <div class="profile">
                <a href="Profile"><img src="${user.getPic()}" alt="Profile picture"></a>
                <span><a href="Profile">${user.getName()}</a></span>
            </div>
        </header>
        <main><br/>
            <form action="Home" method="post" enctype="multipart/form-data">

                <section class="post-compose">
                    <textarea name="content" placeholder="What's Qwackening?"></textarea>
                    <div>
                        <button type="submit" value="post" style="display: inline;">Qwack</button>&emsp;<input style="display:inline" type="file" id="file" name="file">
                    </div>
            </form>
        </section>
        <section class="feed">
            <h1>Feed</h1>
            <c:forEach items="${postList}" var="i">
                <div class="post">
                    <div class="profile img">
                        <a href="Users?username=${i.getUser().getName()}">
                            <img src="${i.getUser().getPic()}" alt="logo" height="45px" width="auto" alt="Profile picture">
                        </a>
                    </div>
                    <div class="post-body">
                        <a href="Users?username=${i.getUser().getName()}">
                            <span class="username">${i.getUser().getName()}&emsp;
                                <div style="font-size: 14px; color: gray">${i.getTimestamp()}</div>
                            </span>   
                        </a>
                        <p>${i.getCaption()}</p>
                        <div class="img">
                            <c:if test = "${i.getPic() != null}">
                                <img src="${i.getPic()}" alt="postPic" height="100px" width="auto"/>
                            </c:if>
                        </div>
                        <div>
                            <form method="post" action="Home"  >
                                <span  style="font-size: 25px; color: #40ff99"> <input type="image" src='views/assets/Like.png' style="background-color: #242444" formenctype="application/x-www-form-urlencoded" id="like" name=${i.getId()}L value="" height="25px" width="auto" title="Like"> ${i.getLikes()}&emsp;&emsp;</span>    
                                <span  style="font-size: 25px; color: #40ff99"> <input type="image" src='views/assets/ReQwack.png' style="background-color: #242444" formenctype="application/x-www-form-urlencoded" id="reqwack" name=${i.getId()}R value="" height="25px" width="auto" title="Reqwack"> ${i.getReqwack()}&emsp;</span>    
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </section>
    </main>
</body>
</html>
