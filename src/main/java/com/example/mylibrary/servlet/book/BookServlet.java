package com.example.mylibrary.servlet.book;

import manager.AuthorManager;
import manager.BookManager;
import model.Author;
import model.Book;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String keyword = req.getParameter("keyword");
        List<Book> result = null;
        if (keyword == null || keyword.equals("")) {
            result = bookManager.getAll();
        } else {
            result = bookManager.search(keyword);
        }
        req.setAttribute("book", result);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req, resp);
    }
    }

