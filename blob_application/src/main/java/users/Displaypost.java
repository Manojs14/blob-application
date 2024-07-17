package users;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Displaypost")
public class Displaypost extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        Connection con = null;
		        PreparedStatement statement = null;
		        ResultSet resultSet = null;

		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sun_base", "root", "Manu2341");

		            String sql = "SELECT id, title, content, media_type FROM posts";
		            statement = con.prepareStatement(sql);
		            resultSet = statement.executeQuery();

		            List<Post> posts = new ArrayList<>();
		            while (resultSet.next()) {
		                Post post = new Post();
		                post.setId(resultSet.getInt("id"));
		                post.setTitle(resultSet.getString("title"));
		                post.setContent(resultSet.getString("content"));
		                post.setMediaType(resultSet.getString("media_type"));
		                posts.add(post);
		            }

		            request.setAttribute("posts", posts);
		            RequestDispatcher dispatcher = request.getRequestDispatcher("viewers.jsp");
		            dispatcher.forward(request, response);
		        } catch (SQLException | ClassNotFoundException e) {
		            e.printStackTrace();
		            response.sendRedirect("error.jsp");
		        } finally {
		            try {
		                if (resultSet != null) resultSet.close();
		                if (statement != null) statement.close();
		                if (con != null) con.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}



