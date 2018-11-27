<%@page import="java.util.List"%>
<%@page import="hu.javagladiators.education.hoe.herolevel.dao.HeroLevel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DBU Manager</title>
        <style>
             table, th, td {
                border: 1px solid black;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Hero levels</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>LevelName</th>
                <th>FromLevel</th>
                <th>UntilLevel</th>
            </tr>
            <%for(HeroLevel d : (List<HeroLevel>)request.getAttribute("dbus")){ %>
            <tr>
                <td>
                    <%= d.getId() %>
                </td>
                <td>
                    <%= d.getLevelName() %>
                </td>
                <td>
                    <%= d.getFromLevel()%>
                </td>
                <td>
                    <%= d.getUntilLevel()%>
                </td>
                <td>
                    <form id="update<%= d.getId() %>" action="Update" method="GET">
                        <input type="hidden" name="id" value="<%= d.getId() %>" form="update<%= d.getId() %>">
                        <input type="submit" value="Update" form="update<%= d.getId() %>">
                    </form>
                    <form id="delete<%= d.getId() %>" action="Delete" method="POST">
                        <input type="hidden" name="id" value="<%= d.getId() %>" form="delete<%= d.getId() %>">
                        <input type="submit" value="Remove" form="delete<%= d.getId() %>">
                    </form>
                </td>
            </tr>
            <%}%>
            
            <tr>
                <form id="save" method="POST" action="Create">
                    <td></td>
                    <td>
                        <input type="text" name="levelName" form="save">
                    </td>
                    <td>
                        <input type="number" name="fromLevel" form="save">
                    </td>
                    <td>
                        <input type="number" name="untilLevel" form="save">
                    </td>
                    <td>
                        <input type="submit" value="Save" form="save">
                    </td>
                </form>    
            </tr>
            
        </table>
    </body>
</html>