package com.imd.telemaco.data;

import java.util.ArrayList;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.entity.Audiovisual;

/**
 * This class extends the DAO class
 *
 * @author Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 5 de jun de 2018 | 09:46:45
 */
public interface DAOAudioVisualSpecialOperations extends DAO<Audiovisual> {

    /**
     * Returns a Audiovisual by the name past
     *
     * @param name
     * @return Audiovisual
     * @throws CloseConnectionException
     * @throws DatabaseException
     */
    public Audiovisual select(String name) throws DatabaseException, CloseConnectionException;

    /**
     * Returns Audiovisual ArrayList that corresponds with the input
     *
     * @param input
     * @return audioVisuals
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public ArrayList<Audiovisual> search(String input) throws DatabaseException, CloseConnectionException;

    /**
     * Returns all the AudioVisuals
     *
     * @return audioVisuals
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public ArrayList<Audiovisual> sellectAllAudioVisuals() throws DatabaseException, CloseConnectionException;
}
