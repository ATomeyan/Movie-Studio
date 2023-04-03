package org.movie.service;

import org.movie.dao.MovieDao;
import org.movie.entity.Movie;
import org.movie.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Artur Tomeyan
 * @date 23/08/2022
 */
public class MovieService implements MovieDao {

    private static final DatabaseConnection DATABASE_CONNECTION = DatabaseConnection.getDatabaseConnection();

    @Override
    public void createTable() {
        String query = "create table if not exists movies (" +
                "id int primary key auto_increment not null," +
                "title varchar(255) not null," +
                "year_of_release int not null)";

        try (Statement statement = DATABASE_CONNECTION.getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        try (Statement statement = DATABASE_CONNECTION.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery("select * from movies");

            if (resultSet.next()) {
                Movie movie = new Movie(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getInt("year_of_release"));
                movies.add(movie);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movies;
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        try (PreparedStatement preparedStatement = DATABASE_CONNECTION.getConnection()
                .prepareStatement("select * from movies where id = ?")) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(new Movie(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getInt("year_of_release")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Movie findByTitle(String title) {
        try (PreparedStatement preparedStatement = DATABASE_CONNECTION.getConnection()
                .prepareStatement("select * from movies where title = ?")) {
            preparedStatement.setString(2, title);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Movie(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getInt("year_of_release"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Movie> findByYear(Integer year) {
        return null;
    }

    @Override
    public Movie findByReleaseYear(Integer year) {
        return null;
    }

    @Override
    public Movie save(Movie movie) {

        try (PreparedStatement preparedStatement = DATABASE_CONNECTION.getConnection()
                .prepareStatement("insert into movies(id, title, year_of_release) VALUES (?, ?, ?)")) {

            preparedStatement.setInt(1, movie.getId());
            preparedStatement.setString(2, movie.getTitle());
            preparedStatement.setInt(3, movie.getYearOfRelease());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movie;
    }

    @Override
    public void remove(Integer id) {

        try (PreparedStatement preparedStatement = DATABASE_CONNECTION.getConnection()
                .prepareStatement("delete from movies where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}