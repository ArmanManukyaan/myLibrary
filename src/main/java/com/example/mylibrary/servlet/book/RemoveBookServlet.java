package com.example.mylibrary.servlet.book;

import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/removeBook")
public class RemoveBookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();
    private static final String UPLOAD_FOLDER = "C:\\Users\\Smart\\IdeaProjects\\myLibrary\\src\\images\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book byId = bookManager.getById(id);
        if (byId != null) {
            if (byId.getPicName() != null
                    || !byId.getPicName().equalsIgnoreCase("null")) {
                File file = new File(UPLOAD_FOLDER + byId.getPicName());
                if (file.exists()) {
                    file.delete();
                }
            }
      bookManager.removeById(id);
        }
        bookManager.removeById(id);
        resp.sendRedirect("/book");
    }
}
