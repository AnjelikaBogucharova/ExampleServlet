<%@page import="com.appline.logic.Model" %>
<%--
  Created by IntelliJ IDEA.
  User: Anjelika
  Date: 20.10.2020
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователями</h1>
  Введите id пользователя (0 - для вывода всего списка)
  <br/>

  Доступно:<%
  Model model = Model.getInstance();
  out.print(model.getFromList().size());
  %>
  <form method="get" name="get">
    <label>ID:
      <input type="text" name="id"><br/>
    </label>
    <button type="submit">Поиск</button>
  </form>

  <a href="addUser.html">Создать нового пользователя</a>
  <a href="deleteUser.html">Удалить пользователя</a>
  <a href="updateUser.html">Изменить данные пользователя</a>
  </body>
</html>
