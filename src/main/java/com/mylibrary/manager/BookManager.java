package com.mylibrary.manager;

import com.mylibrary.dp.DBConnectionProvider;
import com.mylibrary.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private AuthorManager authorManager = new AuthorManager();
    private UserManager userManager = new UserManager();
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void save(Book book) {
        String sql = "INSERT INTO book(title,description,price,pic_name,author_id,user_id) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setInt(3, book.getPrice());
            ps.setString(4, book.getPicName());
            ps.setInt(5, book.getAuthor().getId());
            ps.setInt(6, book.getUser().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
            System.out.println("Book inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> searchByName(String name) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * from book where title LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public Book getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from book where id = " + id);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getAllFromUser(int id) {
        List<Book> bookList = new ArrayList<>();
        String sql = "Select * from book where user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<Book> getAllForAdmin(int id) {
        List<Book> bookList = new ArrayList<>();
        String sql = "Select * from book where user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        String sql = "Select * from book ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void removeById(int bookId) {
        String sql = "DELETE FROM book WHERE id = " + bookId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(Book book) {
        String sql = "UPDATE book Set title = ?,description = ?,price = ?,pic_name = ?,author_id = ?,user_id =? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setInt(3, book.getPrice());
            ps.setString(4, book.getPicName());
            ps.setInt(5, book.getAuthor().getId());
            ps.setInt(6, book.getUser().getId());
            ps.setInt(7, book.getId());
            ps.executeUpdate();
            System.out.println("Table id and name edit");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        int authorId = resultSet.getInt("author_id");
        int userId = resultSet.getInt("user_id");
        return Book.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .price(resultSet.getInt("price"))
                .picName(resultSet.getString("pic_name"))
                .author(authorManager.getById(authorId))
                .user(userManager.getById(userId))
                .build();
    }

    public List<Book> search(String keyword) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * from book where title LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}