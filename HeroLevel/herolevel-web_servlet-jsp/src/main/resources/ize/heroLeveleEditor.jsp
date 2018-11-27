<%@page import="hu.javagladiators.education.hoe.herolevel.dao.HeroLevel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HeroLevelEditor</title>
    </head>
    <body>
        <% HeroLevel dbu=(HeroLevel)request.getAttribute("dbu"); %>
        <form action="/Update" method="POST">
            Id: <%= dbu.getId() %> <input type="hidden" name="id" value="<%= dbu.getId() %>"><br/>
            LevelName: <input type="text" name="levelName" value="<%= dbu.getLevelName() %>"><br/>
            FromLevel: <input type="number" name="fromLevel" value="<%= dbu.getFromLevel() %>"><br/>
            UntilLevel: <input type="number" name="untilLevel" value="<%= dbu.getUntilLevel() %>"><br/>
            <input type="submit" value="Save">
        </form>
    </body>
</html>