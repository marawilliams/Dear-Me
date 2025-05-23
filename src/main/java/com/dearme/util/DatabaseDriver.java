package com.dearme.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDriver {
    private final String sqliteFilename;
    private Connection connection;

    public DatabaseDriver() {
        this.sqliteFilename = "course_review_database.sqlite";
    }

    public DatabaseDriver (String sqlListDatabaseFilename) {
        this.sqliteFilename = sqlListDatabaseFilename;
    }

    /**
     * Connect to a SQLite Database. This turns out Foreign Key enforcement, and disables auto-commits
     * @throws SQLException
     */
    public void connect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            throw new IllegalStateException("The connection is already opened");
        }
        connection = DriverManager.getConnection("jdbc:sqlite:" + sqliteFilename);
        //the next line enables foreign key enforcement - do not delete/comment out
        connection.createStatement().execute("PRAGMA foreign_keys = ON");
        //the next line disables auto-commit - do not delete/comment out
        connection.setAutoCommit(false);
    }

    /**
     * Commit all changes since the connection was opened OR since the last commit/rollback
     */
    public void commit() throws SQLException {
        connection.commit();
    }

    /**
     * Rollback to the last commit, or when the connection was opened
     */
    public void rollback() throws SQLException {
        connection.rollback();
    }

    /**
     * Ends the connection to the database
     */
    public void disconnect() throws SQLException {
        connection.close();
    }

    /**
     * Creates the three database tables Stops, BusLines, and Routes, with the appropriate constraints including
     * foreign keys, if they do not exist already. If they already exist, this method does nothing.
     * As a hint, you'll need to create Routes last, and Routes must include Foreign Keys to Stops and
     * BusLines.
     * @throws SQLException
     */
    public void createTables() throws SQLException {
        try (Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS Entries (ID INTEGER PRIMARY KEY AUTOINCREMENT, Username TEXT NOT NULL, Entry TEXT NOT NULL, Timestamp TEXT NOT NULL, FOREIGN KEY(Username) REFERENCES Users(Username) ON DELETE CASCADE)");

            statement.execute("CREATE TABLE IF NOT EXISTS Users (Username TEXT PRIMARY KEY)");

            statement.execute("CREATE TABLE IF NOT EXISTS Moods (ID INTEGER PRIMARY KEY AUTOINCREMENT, Mood TEXT NOT NULL CHECK (Mood IN ('happy', 'sad', 'angry', 'anxious', 'excited', 'tired', 'content', 'relaxed', 'motivated', 'depressed', 'stressed', 'overworked','irritable','lonely','energetic', 'grateful','hopeful','restless')),\n)");

            statement.execute("CREATE TABLE IF NOT EXISTS Weather (ID INTEGER PRIMARY KEY AUTOINCREMENT, Low INTEGER NOT NULL, High INTEGER NOT NULL, Weather TEXT NOT NULL)");
            statement.close();
        }
    }

    // add Course to database - input a Course object
    public void addCourse(Course course) throws SQLException {
        // sql to add a course to the database
        try (Statement statement = connection.createStatement()) {
            if (courseExists(course)) {
                statement.close();
                throw new IllegalStateException("Course already exists with this Number/Title/Mneumonic");
            }
            statement.execute("INSERT INTO Courses (Number, Title, Mneumonic) VALUES (" + course.getNumber() + ", '" + handleApostrophes(course.getTitle()) + "', '" + handleApostrophes(course.getMnemonic()) + "')");
            statement.close();
        } catch (SQLException e) {
            rollback();
            throw e;
        }
    }
