package com.example.mylibrary.manager;

import com.example.mylibrary.dp.DBConnectionProvider;
import com.example.mylibrary.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void save(Author author) {
        String sql = "INSERT INTO author(name,surname,email,age) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getInt(1));
            }
            System.out.println("Author inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from author where id = " + id);
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Author> getAll() {
        List<Author> authorList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from author");
            while (resultSet.next()) {
                authorList.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    public void removeById(int authorId) {
        String sql = "DELETE FROM author WHERE id = " + authorId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Author author) {
        String sql = "UPDATE author Set name = ?,surname = ?,email = ?,age = ? where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.setInt(5, author.getId());
            ps.executeUpdate();
            System.out.println("Table id and name edit");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        return Author.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .age(resultSet.getInt("age"))
                .build();
    }
}
