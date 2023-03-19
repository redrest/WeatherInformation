<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>WeatherInfo</title>
    </head>
    <body>
    <form action="weather" method="POST">
        <h3>Avalon weather station, australia 2022</h3>
        Month: <select name="month">
        <option>january</option>
        <option>february</option>
        <option>march</option>
        <option>april</option>
        <option>may</option>
        <option>june</option>
        <option>july</option>
        <option>august</option>
        <option>september</option>
        <option>october</option>
        <option>november</option>
        <option>december</option>
    </select>
        <br><br>
        Choose information to know:
        <br><br>
        <input type="checkbox" name="information" value="avgTemp" />Среднемесячная температура воздуха
        <br><br>
        <input type="checkbox" name="information" value="daysMore" />Кол-во дней, когда температура была выше среднемесячной
        <br><br>
        <input type="checkbox" name="information" value="daysLess"/>Кол-во дней, когда температура опускалась ниже среднемесячной
        <br><br>
        <input type="checkbox" name="information" value="threeHottest"/>Три самых теплых дня
        <br><br>
        <input type="submit" value="Show">
    </form>
    </body>
</html>