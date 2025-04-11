<%-- 
    Document   : user
    Created on : Nov. 22, 2022, 10:42:18 p.m.
    Author     : Benji Bettagi, Kevin McLaughlin
--%>
<%@page import="qwack.Database"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="qwack.Post" %>
<%@page import="qwack.Profile" %>
<%@page import="qwack.User" %>

<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link rel="stylesheet" href="views/assets/theme.css"/>
        <style>h1 {
                font-size: 50px;
            }
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
            <div class="profile">
                <a href="Profile"><img src="${user.getPic()}" alt="Profile picture"></a>
                <nav>
                    <ul>
                        <li><a href="Profile">${user.getName()}</a></li>
                    </ul>
                </nav>
            </div>
        </header> 

        <div class ="profileBig">
            <img src="${user.getPic()}">
            <h1>${user.getName()}</h1>
            <form action="Profile" method="post" enctype="multipart/form-data">
                <section>
                    <input name="name" placeholder="Update Username (this may take a moment to update">
                </section>
                <input type="file" id="file" name="file">
                <div>
                    <button type="submit" value="update">Update Profile</button>
                </div>
            </form>
        </div>    
        <section class="feed">
            <h2>Posts</h2>
            <c:forEach items="${postList}" var="i">
                <div class="post">
                    <div class="profile img">
                        
                            <img src="${i.getUser().getPic()}" alt="logo" height="45px" width="auto" alt="Profile picture">
                        
                    </div>
                    <div class="post-body">
                        <span class="username">${i.getUser().getName()}
                                
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
    </body>
</html>
