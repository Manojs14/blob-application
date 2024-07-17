package users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	Connection con;
	  PreparedStatement pstmt;
	  ResultSet rs;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nameemail=request.getParameter("nameemail");
		String pass=request.getParameter("password");
		System.out.println(nameemail+"  "+pass);
		
		
		try {
        	
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("loaded");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sun_base" ,"root","Manu2341");
			System.out.println("conected");
			//check if username, password, email matches
			pstmt=con.prepareStatement("SELECT * FROM users WHERE email = ? Or name= ? ");
			pstmt.setString(1, nameemail);
			pstmt.setString(2, nameemail);
			rs=pstmt.executeQuery();
			System.out.println(rs);
			if(rs.next()) {
				String storedHashedPassword = rs.getString("password");
				 if (BCrypt.checkpw(pass, storedHashedPassword)) {
				HttpSession ses=request.getSession();
				ses.setAttribute("role", rs.getString(5));
				ses.setAttribute("userid", rs.getInt(1));
				request.getRequestDispatcher("AdminDashboard.jsp").forward(request,response);
				}else {
					request.setAttribute("status","noemail");
					request.getRequestDispatcher("index.jsp").forward(request,response);
				}
			}
			else {
				request.setAttribute("status","failure");
				request.getRequestDispatcher("index.jsp").forward(request,response);
			}
		} catch (SQLException |ClassNotFoundException e) {
			e.printStackTrace();
		}
    finally {
    	try {
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	}

}