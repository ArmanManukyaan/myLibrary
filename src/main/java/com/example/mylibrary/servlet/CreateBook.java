package com.example.mylibrary.servlet;

import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/createBook")
public class CreateBook extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    private BookManager bookManager = new BookManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> all = authorManager.getAll();
        req.setAttribute("author", all);
        req.getRequestDispatcher("WEB-INF/createBook.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorID"));
        bookManager.save(Book.builder()
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .price(Integer.parseInt(req.getParameter("price")))
                .author(authorManager.getById(authorId))
                .build());
        resp.sendRedirect("/book");
    }
}