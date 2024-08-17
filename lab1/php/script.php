<?php

//squareCoords
const xSquare = 2;
const ySquare = -2;
//triangleCoords
const x1 = -1/2;
const x2 = 0;
const x3 = 0;
const y1 = 0;
const y2 = 0;
const y3 = -1;

function isThisHit($x, $y, $r){
    //square
    if($r*xSquare*$x>=0 && abs($x)<=xSquare && -$r*ySquare*$y>=0 && abs($y) <=-ySquare){
        return true;
    }
    //triangle
    if(isInTriangle($x, $y, $r)){
        return true;
    }
    //circle
    return isInCircle($x, $y, $r);
}
function isInTriangle($x,$y,$r){
    //проверка суммой площадей
    $s = $r*$r*abs(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2));
    $s1 = abs($x*($r*y1-$r*y2)+$r*x1*($r*y2-$y)+x2*($y-$r*y1));
    $s2 = abs($x*($r*y2-$r*y3)+$r*x2*($r*y3-$y)+x3*($y-$r*y2));
    $s3 = abs($x*($r*y3-$r*y1)+$r*x3*($r*y1-$y)+$r*x1*($y-$r*y3));

    return $s==($s1+$s2+$s3);
}

function isInCircle($x, $y, $r){
    //1 условие координаты к круге
    if(sqrt($x*$x + $y*$y)>abs($r/2)){
        return false;
    }
    return ($r*$x>0 && $r*$y<0);
}

@session_start();

if (!isset($_SESSION["results"])){
    $_SESSION["results"] = array();
}

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $x = $_GET['x'];
    $y = $_GET['y'];
    $r = $_GET['r'];
    $timezone = $_GET['timezone'];
    date_default_timezone_set($timezone);
    if(isThisHit($x, $y, $r)){
        $status = "<span class='success'>Попал</span>";
    }else{
        $status = "<span class='fail'>Промазал</span>";
    }
    $currentTime = date('Y-m-d H:i:s');
    $spentTime = microtime(true) - $_SERVER["REQUEST_TIME_FLOAT"];

    $result = array(
        "x" => $x,
        "y"=> $y,
        "r"=>$r,
        "status"=>$status,
        "currentTime"=>$currentTime,
        "spentTime"=>$spentTime
    );
    array_push($_SESSION["results"], $result);

    echo "<table id='resultTable>'
        <tr>
            <th>x</th>
            <th>y</th>
            <th>r</th>
            <th>Status</th>
            <th>current time</th>
            <th>spent time</th>
        </tr>";

    foreach (array_reverse($_SESSION["results"]) as $row){
        echo "<tr>";
        echo "<td>" . $row["x"] . "</td>";
        echo "<td>" . $row["y"] . "</td>";
        echo "<td>" . $row["r"] . "</td>";
        echo "<td>" . $row["status"] . "</td>";
        echo "<td>" . $row["currentTime"] . "</td>";
        echo "<td>" . $row["spentTime"] . "</td>";
        echo "</tr>";
    }
    echo "</table>";
    // Пример обработки и вывода данных
    //echo "Получены данные: x=$x, y=$y, r=$r, timezone=$timezone";
}