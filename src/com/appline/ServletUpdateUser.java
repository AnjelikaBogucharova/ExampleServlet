package com.appline;

import com.appline.logic.Model;
import com.appline.logic.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/update")
public class ServletUpdateUser extends HttpServlet {
    Model model = Model.getInstance();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer sb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        PrintWriter pw = response.getWriter();
        JsonObject jObj = new JsonParser().parse(sb.toString()).getAsJsonObject();
        request.setCharacterEncoding("UTF-8");
        String name = jObj.get("name").getAsString();
        String surname = jObj.get("surname").getAsString();
        double salary = jObj.get("salary").getAsDouble();
        int id = jObj.get("id").getAsInt();

        User user = new User(name, surname, salary);
        if (model.getFromList().get(id) != null) {
            model.update(user, id);
            response.setContentType("application/text;charset=utf-8");
            pw.print(String.format("User id = %s updated!", id));
        } else {
            response.setContentType("application/text;charset=utf-8");
            pw.print(String.format("User id = %s not found!", id));
        }

    }
}
