/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.User;
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public interface DAOUserSpecialOperations extends DAO<User> {

    /**
     * Select an user by email and password, special method to login.
     *
     * @param email
     * @param password
     * @return
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public User select(String email, String password) throws DatabaseException, CloseConnectionException;

    /**
     * Select an user by email, special method to register.
     *
     * @param email
     * @return
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public User select(String email) throws DatabaseException, CloseConnectionException;

    /**
     * Insert a Audiovisual in the list of user.
     *
     * @param idUser
     * @param idAudiovisual
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public void insertAudiovisual(int idUser, int idAudiovisual) throws DatabaseException, CloseConnectionException;

    /**
     * Select Audiovisuals of the user list.
     *
     * @param id
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public ArrayList<Integer> selectAudiovisuals(int id) throws DatabaseException, CloseConnectionException;

    /**
     * Remove a Audiovisual from the user list.
     *
     * @param idUser
     * @param idAudiovisual
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public void deleteAudiovisual(int idUser, int idAudiovisual) throws DatabaseException, CloseConnectionException;
    
    public void defineAudiovisualTableName(String audiovisualTableName);
}
