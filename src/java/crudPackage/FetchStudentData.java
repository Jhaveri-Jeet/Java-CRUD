package crudPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FetchStudentData", urlPatterns = {"/FetchStudentData"})
public class FetchStudentData extends HttpServlet {

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
          PrintWriter out = response.getWriter();
          try {
               DriverManager.registerDriver(new com.mysql.jdbc.Driver());
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUD", "root", "");
               PreparedStatement stmt = con.prepareStatement("SELECT * FROM StudentData ORDER BY Id DESC");
               ResultSet data = stmt.executeQuery();
               while (data.next()) {
                    int singleStudentId = data.getInt("Id");
                    String singleStudentProjectName = data.getString("ProjectName");
                    int singleStudentTeamSize = data.getInt("TeamSize");
                    String singleStudentTechnology = data.getString("Technology");
                    out.println("<tr>");
                    out.println("<th scope=\"row\">" + data.getInt("Id") + "</th>");
                    out.println("<td>" + data.getString("ProjectName") + "</td>");
                    out.println("<td>" + data.getInt("TeamSize") + "</td>");
                    out.println("<td>" + data.getString("Technology") + "</td>");
                    out.println("<td>");
                    out.println("<button type=\"button\" class=\"btn btn-link btn-sm p-3\" data-ripple-color=\"dark\" title=\"Update\"  data-mdb-toggle=\"modal\" data-mdb-target=\"#updateModal\" onclick=\"UpdateData(" + singleStudentId + ", '" + singleStudentProjectName + "', " + singleStudentTeamSize + ", '" + singleStudentTechnology + "')\">");
                    out.println("<i class=\"fas fa-pencil\"></i>");
                    out.println("</button>");
                    out.println("</td>");
                    out.println("<td>");
                    out.println("<button type=\"button\" class=\"btn btn-link btn-sm p-3\" data-ripple-color=\"dark\" title=\"Delete\" data-mdb-toggle=\"modal\" data-mdb-target=\"#deleteModal\" onclick=\"DeleteData(" + singleStudentId + ")\">");
                    out.println("<i class=\"fas fa-times\"></i>");
                    out.println("</button>");
                    out.println("</td>");
                    out.println("</tr>");
               }

          } catch (SQLException ex) {
               System.out.println(ex.getMessage());
          }
     }
}
