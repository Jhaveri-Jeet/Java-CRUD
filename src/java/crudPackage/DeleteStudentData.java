package crudPackage;

import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteStudentData", urlPatterns = {"/DeleteStudentData"})
public class DeleteStudentData extends HttpServlet {
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("application/json");
          String id = request.getParameter("deleteStudentId");
          
          try {
               DriverManager.registerDriver(new com.mysql.jdbc.Driver());
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUD", "root", "");
               PreparedStatement stmt = con.prepareStatement("DELETE FROM StudentData WHERE Id = ?");
               stmt.setString(1, id);
               stmt.executeUpdate();
               
          } catch (SQLException ex) {
               System.out.println(ex.getMessage());
          }
     }
}
