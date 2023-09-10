package crudPackage;

import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateStudentData", urlPatterns = {"/UpdateStudentData"})
public class UpdateStudentData extends HttpServlet {

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("application/json");

          String id = request.getParameter("updatedStudentId");
          String projectName = request.getParameter("updatedProjectName");
          String teamSize = request.getParameter("updatedTeamSize");
          String technology = request.getParameter("updatedTechnology");

          try {
               DriverManager.registerDriver(new com.mysql.jdbc.Driver());
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUD", "root", "");
               PreparedStatement stmt = con.prepareStatement("UPDATE StudentData SET ProjectName = ? , TeamSize = ?, Technology = ? WHERE Id = ?");
               stmt.setString(1, projectName);
               stmt.setString(2, teamSize);
               stmt.setString(3, technology);
               stmt.setString(4, id);
               stmt.executeUpdate();
               
          } catch (SQLException ex) {
               System.out.println(ex.getMessage());
          }
     }
}
