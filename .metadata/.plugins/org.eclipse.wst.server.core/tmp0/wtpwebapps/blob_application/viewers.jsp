<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="users.Post" %>
<html>
<head>
    <title>Display Posts</title>
</head>
<body>
    <h1>Posts</h1>
    
    <%-- Check if posts are available --%>
    <% if (posts != null && !posts.isEmpty()) { %>
        <%-- Loop through each post --%>
        <% for (Post post : posts) { %>
            <div>
                <h3><%= post.getTitle() %></h3>
                <p><%= post.getContent() %></p>
                <p>Media Type: <%= post.getMediaType() %></p>
                
                <%-- Display media based on media type --%>
                <% if (post.getMediaType() != null && !post.getMediaType().isEmpty()) { %>
                    <%-- Check if media type is image --%>
                    <% if (post.getMediaType().startsWith("image/")) { %>
                        <img src="getImage.jsp?id=<%= post.getId() %>" alt="Image" style="max-width: 100%;" />
                    <% } else if (post.getMediaType().startsWith("video/")) { %>
                        <%-- Display video --%>
                        <video controls style="max-width: 100%;">
                            <source src="getVideo.jsp?id=<%= post.getId() %>" type="<%= post.getMediaType() %>">
                            Your browser does not support the video tag.
                        </video>
                    <% } else { %>
                        <p>Unsupported media type: <%= post.getMediaType() %></p>
                    <% } %>
                <% } else { %>
                    <p>No media available for this post.</p>
                <% } %>
                
                <hr/>
            </div>
        <% } %>
    <% } else { %>
        <p>No posts found.</p>
    <% } %>

</body>
</html>
