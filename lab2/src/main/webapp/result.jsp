
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="logic.Point, java.util.List" %>
<html>
<head>
    <title>Results</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Advent+Pro:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/choices.js@9.0.1/public/assets/styles/choices.min.css" />
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    <link href="styles/media.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<a href="/"><h1>На главную</h1></a><br>
<table id='resultTable'>
    <div id="tableHeader">
        <tr>
            <th>x</th>
            <th>y</th>
            <th>r</th>
            <th>Status</th>
        </tr>
    </div>
    <tbody id  ="megaTbodyEshkere">
    <%
        List<Point> points = (List<Point>) application.getAttribute("results");
        if (points!=null){
            for(Point point:points){
                if (point.getId().equals(request.getSession().getAttribute("id"))){
    %>
    <tr>
        <td>
            <%if (point.getX()== (int) point.getX()){%>
            <%=(int)(point.getX()) %>
            <%}else{%>
            <%=point.getX()%>
            <%}%>
        </td>
        <td>
            <%if (point.getY()== (int) point.getY()){%>
            <%=(int)(point.getY()) %>
            <%}else{%>
            <%=point.getY()%>
            <%}%>
        </td>
        <td>
            <%if (point.getR()== (int) point.getR()){%>
            <%=(int)(point.getR()) %>
            <%}else{%>
            <%=point.getR()%>
            <%}%>
        </td>

        <%
            String status = point.getStatus();
            if (status.equals("true")) {
        %>
        <td><span style="color: #05da00">&#9745;</span></td>
        <%
        } else if (status.equals("false")) {
        %>
        <td><span style="color: red">&#9746;</span></td>
        <%
        }else{
        %>
        <td><%=status%></td>
        <%
            }
        %>
    </tr>
    <%
                }
            }
        }
    %>
    </tbody>
</table>
<script type="text/javascript" src="reloadScript.js"></script>
</body>
</html>
