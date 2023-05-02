package com.example.mylibrary.servlet.book;

import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateBook")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,//5mb
        maxRequestSize = 1024 * 1024 * 10, //10mb
        fileSizeThreshold = 1024 * 1024 * 1
)
public class UpdateBookServlet extends HttpServlet {
    private static final String UPLOAD_FOLDER = "C:\\Users\\Smart\\IdeaProjects\\myLibrary\\src\\images\\";
    private AuthorManager authorManager = new AuthorManager();
    private BookManager bookManager = new BookManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Book byId = bookManager.getById(id);
        if (byId != null) {
            if (byId.getPicName() != null || !byId.getPicName().equalsIgnoreCase("null")) {
                File file = new File(UPLOAD_FOLDER + byId.getPicName());
                if (file.exists()) {
                    file.delete();
                }
            }
            Book book = bookManager.getById(id);
            List<Author> all = authorManager.getAll();
            req.setAttribute("author", all);
            req.setAttribute("book", book);
            req.getRequestDispatcher("WEB-INF/updateBook.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        Part profilePic = req.getPart("profilePic");
        String picName = null;
        if (profilePic != null && profilePic.getSize() > 0) {
            picName = System.nanoTime() + "_" + profilePic.getSubmittedFileName();
            profilePic.write(UPLOAD_FOLDER + picName);
        }
        int authorId = Integer.parseInt(req.getParameter("authorID"));
        bookManager.update(Book.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .title(req.getParameter("title"))
                .description(req.getParameter("description"))
                .price(Integer.parseInt(req.getParameter("price")))
                .author(authorManager.getById(authorId))
                .picName(picName)
                .user(userManager.getById(userId))
                .build());
        resp.sendRedirect("/book");
    }

}
