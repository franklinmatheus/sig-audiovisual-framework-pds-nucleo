package com.imd.telemaco.data;

import java.util.ArrayList;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Audiovisual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 5 de jun de 2018 | 09:52:54
 */
public abstract class AudiovisualDAO implements DAOAudioVisualSpecialOperations {

    protected Connection connection;

    protected AudiovisualDAO() throws DatabaseException {
        this.connection = ConnectionFactory.getConnection();
    }
    
    /**
     * Method to know if already exists a connection with the db
     *
     * @throws DatabaseException
     */
    protected void startsConnection() throws DatabaseException {
        try {
            if (this.connection.isClosed()) {
                this.connection = ConnectionFactory.getConnection();
            }
        } catch (SQLException e) {
            throw new DatabaseException();
        }
    }

    @Override
    public abstract void insert(Audiovisual audioVisual) throws DatabaseException, CloseConnectionException;

    public abstract String sqlSelectId(int id);

    public abstract Audiovisual fillAudioVisualAttributes(int id, ResultSet result) throws DatabaseException;

    @Override
    public Audiovisual select(int id) throws DatabaseException, CloseConnectionException {
        String sql = sqlSelectId(id);
        Audiovisual audioVisual;
        try {
            this.startsConnection();

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next()) {
                audioVisual = fillAudioVisualAttributes(id, result);
            } else {
                audioVisual = null;
            }

            return audioVisual;
        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            /*try { connection.close(); } 
            catch (SQLException e) { throw new CloseConnectionException(); }*/
        }
    }

    public abstract String sqlSelectName(String name);

    @Override
    public Audiovisual select(String name) throws DatabaseException, CloseConnectionException {
        String sql = sqlSelectName(name);
        Audiovisual audioVisual = null;

        try {
            this.startsConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);

            if (result.next()) {
                int id = result.getInt("id");
                audioVisual = select(id);
            }

            return audioVisual;
        } catch (SQLException e) {
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }

    public abstract String sqlDelete(int id);

    @Override
    public void delete(Audiovisual audioVisual) throws DatabaseException, CloseConnectionException {
        String sql = sqlDelete(audioVisual.getId());
        try {
            this.startsConnection();

            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }

    @Override
    public abstract void update(Audiovisual object) throws DatabaseException, CloseConnectionException;

    public abstract String sqlSearch(String input);

    @Override
    public ArrayList<Audiovisual> search(String input) throws DatabaseException, CloseConnectionException {
        String sql = sqlSearch(input);
        ArrayList<Audiovisual> results = new ArrayList<>();

        try {
            startsConnection();

            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);

            while (set.next()) {
                int id = set.getInt("id");
                Audiovisual audioVisual = select(id);
                results.add(audioVisual);
            }

            return results;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new DatabaseException();
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new CloseConnectionException();
            }
        }
    }

    public abstract String sqlSellectAllAudioVisual();

    @Override
    public ArrayList<Audiovisual> sellectAllAudioVisuals() throws DatabaseException, CloseConnectionException {
        ArrayList<Audiovisual> audioVisuals = new ArrayList<>();
        String sql = sqlSellectAllAudioVisual();

        try {
            this.startsConnection();

            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
            
            while (result.next()) {
                int id = result.getInt("id");
                Audiovisual audioVisual = select(id);
                audioVisuals.add(audioVisual);
            }
            
            return audioVisuals;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                throw new CloseConnectionException();
//            }
        }
    }
}
