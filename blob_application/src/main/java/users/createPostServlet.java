package users;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/createPostServlet")
@MultipartConfig
public class createPostServlet extends HttpServlet {
	
	Connection con;
	  PreparedStatement statement;
	  ResultSet rs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession ses=request.getSession();
    	String role=(String)ses.getAttribute("role");
    	int userid=(Integer)ses.getAttribute("userid");
    	String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        // Part for handling file upload
        Part filePart = request.getPart("media");
        InputStream inputStream = null;
        String fileType = null;
        
        if (filePart != null) {
            inputStream = filePart.getInputStream();            
            fileType = filePart.getContentType();
        }
    	
        // Database connection parameters
        
        String sql = "INSERT INTO posts (title, content, media, media_type, created_at, userid) VALUES (?, ?, ?, ?, ?,?)";

        try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 response.sendRedirect("AdminDashboard.jsp?error=Database error occurred.");
			}
        	System.out.println("loaded");
        	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sun_base" ,"root","Manu2341");
            statement = con.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, content);

            if (inputStream != null) {
                // Set parameters for file data
                statement.setBlob(3, inputStream);
                statement.setString(4, fileType);
            } else {
                // Handle if no file is uploaded
                statement.setNull(3, java.sql.Types.BLOB);
                statement.setNull(4, java.sql.Types.VARCHAR);
            }

            // Set created_at to current timestamp
            statement.setObject(5, LocalDateTime.now()); // Use LocalDateTime for DATETIME
            statement.setInt(6, userid);
            // Execute the statement
            int r=statement.executeUpdate();
            if(r>0) {
            // Redirect to a success page or another appropriate location
            response.sendRedirect("Displaypost");
            }else {
            	response.sendRedirect("AdminDashboard.jsp?error=Database error occurred.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
            response.sendRedirect("AdminDashboard.jsp?error=Database error occurred.");
        }
    }
}