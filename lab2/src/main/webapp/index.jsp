<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logic.Point, java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>lab2</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Advent+Pro:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
    <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    <link href="styles/media.css" rel="stylesheet" type="text/css"/>

</head>

<body>
<header id="head">
    <h1>Веб-программирование, Лабораторная работа №2, Вариант 333321<br/></h1>
    <h2>Алхимовици Арсений, P3210</h2>
</header>
<div class="mainPage">
    <div class="message" id="mainMessage">
        Info Box
    </div>
    <div class="container">
        <div class="item left">
<%--            <form id="mainForm" type="submit" action="ControllerServlet" method="post" onsubmit="return checkForm()">--%>
<%--                <label class="input" for="xInput">Введите X: </label>--%>
<%--                <input class="input" name="x" id="xInput" type="text" maxlength="5"/><br/><br/>--%>

<%--                <label class="input" for="yInput">Введите Y: </label>--%>
<%--                <input class="input" name="y" id="yInput" type="text" maxlength="5"/><br/><br/>--%>

<%--                <label class="input" for="rInput">Выберите R:</label>--%>
<%--                <select class="js-choice" name="r" id="rInput">--%>
<%--                    <option value=1>1</option>--%>
<%--                    <option value=2>2</option>--%>
<%--                    <option value=3>3</option>--%>
<%--                    <option value=4>4</option>--%>
<%--                    <option value=5>5</option>--%>
<%--                </select>--%>
<%--                <br/><br/>--%>
<%--                <button type="submit" name="submit" id = "mainButton">Подтвердить</button>--%>
<%--            </form>--%>

<%--            <form id="mainForm" onsubmit="submitForm(); return false;">--%>
                <label class="input" for="xInput">Выберите X:</label>
                <select class="select" name="x" id="xInput">
                    <option value=-4>-4</option>
                    <option value=-3>-3</option>
                    <option value=-2>-2</option>
                    <option value=-1>-1</option>
                    <option value=0>0</option>
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                    <option value=4>4</option>
                </select><br/><br/>

                <label class="input" for="yInput">Введите Y: </label>
                <input class="input" name="y" id="yInput" type="text" maxlength="6"/><br/><br/>

                <label class="input" for="rInput">Выберите R:</label>
                <select class="select" name="r" id="rInput">
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                </select>
                <br/><br/>
                <button id="mainButton" type="button">Подтвердить</button>
<%--            </form>--%>

<%--            <div id="mainForm">--%>
<%--&lt;%&ndash;                <label class="input" for="xInput">Введите X: </label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <input class="input" name="x" id="xInput" type="text" maxlength="5"/><br/><br/>&ndash;%&gt;--%>
<%--                <label class="input" for="xInput">Выберите X:</label>--%>
<%--                <select class="select" name="x" id="xInput">--%>
<%--                    <option value=-4>-4</option>--%>
<%--                    <option value=-3>-3</option>--%>
<%--                    <option value=-2>-2</option>--%>
<%--                    <option value=-1>-1</option>--%>
<%--                    <option value=0>0</option>--%>
<%--                    <option value=1>1</option>--%>
<%--                    <option value=2>2</option>--%>
<%--                    <option value=3>3</option>--%>
<%--                    <option value=4>4</option>--%>
<%--                </select><br/><br/>--%>

<%--                <label class="input" for="yInput">Введите Y: </label>--%>
<%--                <input class="input" name="y" id="yInput" type="text" maxlength="6"/><br/><br/>--%>

<%--                <label class="input" for="rInput">Выберите R:</label>--%>
<%--                <select class="select" name="r" id="rInput">--%>
<%--                    <option value=1>1</option>--%>
<%--                    <option value=2>2</option>--%>
<%--                    <option value=3>3</option>--%>
<%--                </select>--%>
<%--                <br/><br/>--%>
<%--                <button id="mainButton" type="button" onclick="doPost()">Подтвердить</button>--%>
<%--            </div>--%>
        </div>
        <div class="item right">
            <canvas id="canvasPlot" width="300" height="300"></canvas>
        </div>
    </div>
</div>
<hr>
<div>
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
                List<Point> points;
                if (application.getAttribute("results") instanceof List<?>){
                    points = (List<Point>)(application.getAttribute("results"));
                }else{
                    points = new ArrayList<>();
                }

                //Point lastPoint;
                if (points!=null){
                    for(Point point:points){
                        if (point.getId().equals(request.getSession().getAttribute("id"))){%>
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
<%--    <c:set var="age" value="18" />--%>
<%--    <p>Adult: ${age >= 18}</p>    --%>
</div>
<script type="module" src="canvas.js"></script>
<script type="module" src="script.js"></script>
</body>
</html>