package com.example.mylibrary.servlet.book;

import manager.AuthorManager;
import manager.BookManager;
import manager.UserManager;
import model.Author;
import model.Book;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/createBook")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,//5mb
        maxRequestSize = 1024 * 1024 * 10, //10mb
        fileSizeThreshold = 1024 * 1024 * 1
)
public class CreateBookServlet extends HttpServlet {
    private static final String UPLOAD_FOLDER = "C:\\Users\\Smart\\IdeaProjects\\myLibrary\\src\\images\\";
    private AuthorManager authorManager = new AuthorManager();
    private BookManager bookManager = new BookManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> all = authorManager.getAll();
        req.setAttribute("author", all);
        req.getRequestDispatcher("WEB-INF/createBook.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int authorId = Integer.parseInt(req.getParameter("authorID"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        Part profilePicPart = req.getPart("profilePic");
        String picName = null;
        if (profilePicPart != null && profilePicPart.getSize() > 0) {
            picName = System.nanoTime() + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(UPLOAD_FOLDER + picName);
        }
        bookManager.save(Book.builder()
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .price(Integer.parseInt(req.getParameter("price")))
                .picName(picName)
                .author(authorManager.getById(authorId))
                .user(userManager.getById(id))
                .build());
        resp.sendRedirect("/book");
    }
}