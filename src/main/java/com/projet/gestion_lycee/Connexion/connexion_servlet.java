package com.projet.gestion_lycee.Connexion;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name ="connexion_servlet", value = "/connexion_servlet")
public class connexion_servlet extends HttpServlet {

    private Connection connectToDatabase() throws Exception {
        String url = "jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT";
        String username = "root";
        String password = ""; // Remplacez par votre mot de passe MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    private boolean checkCredentials(String table, String identification, String motdepasse) throws Exception {
        Connection connection = connectToDatabase();
        String query = "SELECT * FROM " + table + " WHERE identification = ? AND motdepasse = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, identification);
        preparedStatement.setString(2, motdepasse);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean isValid = resultSet.next();
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return isValid;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identification = request.getParameter("identification");
        String motdepasse = request.getParameter("password");

        try {
            RequestDispatcher dispatcher;
            if (checkCredentials("proviseur", identification, motdepasse)) {
                dispatcher = request.getRequestDispatcher("interfaceproviseur.jsp");
            } else if (checkCredentials("login_enseignant", identification, motdepasse)) {
                dispatcher = request.getRequestDispatcher("connexion_enseignant.jsp");
            } else if (checkCredentials("login_administration", identification, motdepasse)) {
                dispatcher = request.getRequestDispatcher("connexion_administration.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("index.jsp"); // Rester sur la page de login en cas d'échec
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response); // En cas d'erreur, rediriger vers la page de login
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
