package com.example.mylibrary.servlet.book;

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

@WebServlet("/updateBook")
public class UpdateBookServlet extends HttpServlet {
    private AuthorManager authorManager = new AuthorManager();
    private BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book book = bookManager.getById(id);
        List<Author> all = authorManager.getAll();
        req.setAttribute("author", all);
        req.setAttribute("book", book);
        req.getRequestDispatcher("WEB-INF/updateBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorID"));
        bookManager.update(Book.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .price(Integer.parseInt(req.getParameter("price")))
                .picName(req.getParameter("picName"))
                .author(authorManager.getById(authorId))
                .build());
        resp.sendRedirect("/book");
    }
}