package crudPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddStudentData", urlPatterns = {"/AddStudentData"})
public class AddStudentData extends HttpServlet {

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("application/json");
          PrintWriter out = response.getWriter();
          String projectName = request.getParameter("projectName");
          String teamSize = request.getParameter("teamSize");
          String technology = request.getParameter("technology");
          try {
               DriverManager.registerDriver(new com.mysql.jdbc.Driver());
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUD", "root", "");
               PreparedStatement stmt = con.prepareStatement("INSERT INTO StudentData (ProjectName, TeamSize, Technology) VALUES (?,?,?)");
               stmt.setString(1, projectName);
               stmt.setString(2, teamSize);
               stmt.setString(3, technology);
               stmt.executeUpdate();

          } catch (SQLException ex) {
               out.print(ex);
          }
     }
}
