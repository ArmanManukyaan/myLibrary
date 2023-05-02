package com.mylibrary.servlet.author;

import com.mylibrary.manager.AuthorManager;
import com.mylibrary.model.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/updateAuthor")
public class UpdateAuthorServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Author author = authorManager.getById(id);
        req.setAttribute("author",author);
        req.getRequestDispatcher("WEB-INF/updateAuthor.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorManager.update(Author.builder()
                        .id( Integer.parseInt(req.getParameter("id")))
                        .name(req.getParameter("name"))
                        .surname(req.getParameter("surname"))
                        .email(req.getParameter("email"))
                        .age( Integer.parseInt(req.getParameter("age")))
                .build());
        resp.sendRedirect("/author");
    }
}
