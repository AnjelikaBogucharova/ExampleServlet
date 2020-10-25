package com.appline;

import com.appline.logic.Model;
import com.appline.logic.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        StringBuffer sb = new StringBuffer();
        String line;
        try{
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null){
                sb.append(line);
            }
        }catch (Exception e){
            System.out.println("Error");
        }

        PrintWriter pw = response.getWriter();
        JsonObject jsonObj = new JsonParser().parse(sb.toString()).getAsJsonObject();
        String idBody = jsonObj.get("id").getAsString();
        int id = Integer.parseInt(idBody);
        if (id == 0) {
            response.setContentType("application/json;charset=utf-8");
            pw.print(gson.toJson(model.getFromList()));

        } else if (id > 0) {
            if (id > model.getFromList().size()) {
                response.setContentType("application/text;charset=utf-8");
                pw.print(String.format("User id = %s not found!", id));
            } else {
                response.setContentType("application/json;charset=utf-8");
                pw.print(gson.toJson(model.getFromList().get(id)));
            }
        } else {
            response.setContentType("application/text;charset=utf-8");
            pw.print("Incorrect ID!");
        }
    }

    //    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//
//        PrintWriter pw = response.getWriter();
//
//        int id = Integer.parseInt(request.getParameter("id"));
//        if(id == 0){
//            pw.print("<html>"+
//                    "<h3>Доступные пользователи:</h3><br/>"+
//                    "ID пользователя: "+
//                    "<ul>");
//
//            for(Map.Entry<Integer, User> entry : model.getFromList().entrySet()){
//                pw.print("<li>" + entry.getKey() + "</li>" +
//                        "<ul>" +
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
//                        "</ul>");
//            }
//            pw.print("</ul>"+
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        }else if(id>0){
//            if(id > model.getFromList().size()){
//                pw.print("<html>"+
//                        "<h3>Такого пользователя нет!</h3>"+
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            }else{
//                pw.print("<html>"+
//                        "<h3>Запрошенный пользователь:</h3><br/>"+
//                        "Имя: " + model.getFromList().get(id).getName() + "<br/>"+
//                        "Фамилия: " + model.getFromList().get(id).getSurname() + "<br/>"+
//                        "Зарплата: " + model.getFromList().get(id).getSalary() + "<br/>"+
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html>");
//            }
//        }else{
//            pw.print("<html>"+
//                    "<h3>ID должен быть больше 0!</h3>"+
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html>");
//        }
//    }

}
