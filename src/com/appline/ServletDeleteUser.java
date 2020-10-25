package com.appline;

import com.appline.logic.Model;
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

@WebServlet(urlPatterns = "/delete")
public class ServletDeleteUser extends HttpServlet {
    Model model = Model.getInstance();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
        int id = Integer.parseInt(jsonObj.get("id").getAsString());
        if (model.getFromList().get(id) != null) {
            model.remove(id);
            response.setContentType("application/text;charset=utf-8");
            pw.print(String.format("User id = %s deleted!", id));

        } else {
            response.setContentType("application/text;charset=utf-8");
            pw.print(String.format("User id = %s not found!", id));
        }
    }
}
