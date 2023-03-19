package com.example.weatherinformation;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/weather")
public class Weather extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String Month = request.getParameter("month");
        ArrayList<String> information = new ArrayList<>();
        if(request.getParameterValues("information") != null)
            Collections.addAll(information, request.getParameterValues("information"));

        String filename = "\\lib\\" + Month + ".txt";
        InputStream file = getServletContext().getResourceAsStream(filename);
        InputStreamReader filer = new InputStreamReader(file);

        ArrayList<Double> days = new ArrayList<Double>();

        try (BufferedReader br = new BufferedReader(filer)) {
            while (br.ready()) {
                days.add(Double.valueOf(br.readLine().trim()));
            }
        }

        ArrayList<Double> threeHottest = new ArrayList<Double>();
        ThreeHottestDays(days, threeHottest);
        double avgTemp = AverageTemperature(days);
        int daysMore = DaysMoreThenAvg(avgTemp, days);
        int daysLess = DaysLessThenAvg(avgTemp, days);

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        try {
            writer.println("<HTML>");
            writer.println("<!DOCTYPE html>");
            writer.println("<HEAD>");
            writer.println("</HEAD>");
            writer.println("<BODY>");
            writer.println("<p>Выбранный месяц: " + Month + "</p>");
            if (information.contains("avgTemp") && information.contains("daysMore") && information.contains("daysLess") && information.contains("threeHottest")){
                writer.println("<p>Среднемесячная температура: " + avgTemp + "</p>");
                writer.println("<p>Кол-во дней, когда температура выше чем среднемесячная: " + daysMore + "</p>");
                writer.println("<p>Кол-во дней, когда температура ниже чем среднемесячная: " + daysLess + "</p>");
                String hot = "";
                for (Double item: threeHottest) {
                    hot += item + "; ";
                }
                writer.println("<p>Три самых жарких дня за месяц: " + hot + "</p>");
            } else if (information.contains("avgTemp") && information.contains("daysMore") && information.contains("daysLess")){
                writer.println("<p>Среднемесячная температура: " + avgTemp + "</p>");
                writer.println("<p>Кол-во дней, когда температура выше чем среднемесячная: " + daysMore + "</p>");
                writer.println("<p>Кол-во дней, когда температура ниже чем среднемесячная: " + daysLess + "</p>");
            } else if (information.contains("avgTemp") && information.contains("daysLess")) {
                writer.println("<p>Среднемесячная температура воздуха: " + avgTemp + "</p>");
                writer.println("<p>Кол-во дней, когда температура ниже чем среднемесячная: " + daysLess + "</p>");
            } else if (information.contains("avgTemp") && information.contains("daysMore")) {
                writer.println("<p>Среднемесячная температура воздуха: " + avgTemp + "</p>");
                writer.println("<p>Кол-во дней, когда температура выше чем среднемесячная: " + daysMore + "</p>") ;
            } else if (information.contains("avgTemp") && information.contains("threeHottest")) {
                writer.println("<p>Среднемесячная температура: " + avgTemp + "</p>");
                String hot = "";
                for (Double item: threeHottest) {
                    hot += item + "; ";
                }
                writer.println("<p>Три самых жарких дня за месяц: " + hot + "</p>");
            } else if (information.contains("avgTemp")) {
                writer.println("<p>Среднемесячная температура воздуха: " + avgTemp + "</p>");
            } else if (information.contains("threeHottest")) {
                String hot = "";
                for (Double item: threeHottest) {
                    hot += item + "; ";
                }
                writer.println("<p>Три самых жарких дня за месяц: " + hot + "</p>");
            } else {
                writer.println("Выберите нужную информацию для вывода!");
            }
            writer.println("</BODY></HTML>");
        } finally {
            writer.close();
        }
    }
    static Double AverageTemperature(ArrayList<Double> list) {
        double avg = 0.0;
        for (Double item: list) {
            avg += item;
        }
        avg /= list.size();
        return avg;
    }
    static int DaysMoreThenAvg(Double avgTemp, ArrayList<Double> list) {
        int counter = 0;
        for(Double item: list) {
            if(item > avgTemp) {
                counter++;
            }
        }
        return counter;
    }
    static int DaysLessThenAvg(Double avgTemp, ArrayList<Double> list) {
        int counter = 0;
        for (Double item: list){
            if(item < avgTemp) {
                counter++;
            }
        }
        return counter;
    }
    static ArrayList<Double> ThreeHottestDays(ArrayList<Double> list, ArrayList<Double> hottest) {
        Collections.sort(list);
        hottest.add(list.get(list.size() - 1));
        hottest.add(list.get(list.size() - 2));
        hottest.add(list.get(list.size() - 3));
        return hottest;
    }
}