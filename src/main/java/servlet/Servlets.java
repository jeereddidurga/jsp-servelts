package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Servlets extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "110845@Durga");
			
			String sql = "insert into student(name, email, password) values(?, ?, ?)";
			
			PreparedStatement pmst = connect.prepareStatement(sql);
			
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			
			pmst.setString(1, name);
			pmst.setString(2, email);
			pmst.setString(3, password);
			
			int i = pmst.executeUpdate();
			
			PrintWriter pw = resp.getWriter();
			
			if (i > 0) {
				pw.println("Successfully Inserted");
			} else {
				pw.println("Error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}