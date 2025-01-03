package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySQLUserDao implements GenericDAO<UserData> {

private Connection connection;

public MySQLUserDao() {
    this.connection = DatabaseSingleton.getInstance().getConnection();
}


    @Override
    public void save(UserData entity) {
        String query = "INSERT INTO users (name) VALUES (?)";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, entity.getName());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserData findById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){  //déplace le curseur dans le ResultSet vers la ligne suivante et retourne true si une ligne existe, sinon false si le curseur est déjà à la fin des résultats
                return new UserData(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserData> findAll() {
        List<UserData> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new UserData(resultSet.getString("name")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UserData entity, int id) {
        String query = "UPDATE users SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
