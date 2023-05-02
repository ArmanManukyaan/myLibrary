package com.mylibrary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {
    private static final String UPLOAD_FOLDER = "C:\\Users\\Smart\\IdeaProjects\\myLibrary\\src\\images\\";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picName = req.getParameter("picName");
        File imageFile = new File(UPLOAD_FOLDER + picName);
        if (imageFile.exists()) {
            try (FileInputStream inStream = new FileInputStream(imageFile)) {

                resp.setContentType("image/jpeg");
                resp.setContentLength((int) imageFile.length());

                OutputStream outputStream = resp.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
