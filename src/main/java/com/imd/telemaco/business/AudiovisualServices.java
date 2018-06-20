package com.imd.telemaco.business;

import java.util.ArrayList;

import com.imd.telemaco.business.exception.AudiovisualInvalidException;
import com.imd.telemaco.business.exception.BusinessException;
import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.NoResultsException;
import com.imd.telemaco.business.exception.RatingInvalidException;
import com.imd.telemaco.data.AudiovisualDAO;
import com.imd.telemaco.data.DAORatingSpecialOperations;
import com.imd.telemaco.data.RatingDAO;
import com.imd.telemaco.entity.Audiovisual;
import com.imd.telemaco.entity.Rating;
import com.imd.telemaco.entity.User;

/**
 *
 * @author Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @author Valmir Correa (valmir.jr.correa@gmail.com)
 * @version 4 de jun de 2018 | 14:14:52
 */
public abstract class AudiovisualServices {

    /**
     * Default Constructor
     */
    protected AudiovisualDAO audiovisualDAO;

    public AudiovisualDAO getAudiovisualDAO() {
        return audiovisualDAO;
    }

    public void setAudiovisualDAO(AudiovisualDAO audiovisualDAO) {
        this.audiovisualDAO = audiovisualDAO;
    }

    public void validate(Audiovisual audiovisual, User user)
            throws AudiovisualInvalidException, DatabaseException, CloseConnectionException, BusinessException {

        this.validateName(audiovisual);
        this.validateID(audiovisual, user);
        this.AudiovisualExistInDB(audiovisual);
    }

    public abstract void validate(Audiovisual audiovisual)
            throws AudiovisualInvalidException, DatabaseException, CloseConnectionException, BusinessException;

    public abstract ArrayList<Audiovisual> recommend();

    public void validateName(Audiovisual audiovisual) throws AudiovisualInvalidException {
        if (audiovisual.getName().isEmpty() || audiovisual.getName() == null) {
            throw new AudiovisualInvalidException("Nome da série inválido!");
        }

    }

    public void validateID(Audiovisual audiovisual, User user) throws AudiovisualInvalidException {
        if (!(audiovisual.getId() == user.getId())) {
            throw new AudiovisualInvalidException("Id inválido!");
        }

    }

    public void AudiovisualExistInDB(Audiovisual audiovisual)
            throws AudiovisualInvalidException, DatabaseException, CloseConnectionException, BusinessException {

        Audiovisual audiovisualRemove = audiovisualDAO.select(audiovisual.getId());
        if (null != audiovisualRemove) {
            try {
                throw new BusinessException("A serie " + "'" + audiovisual.getName() + "'" + " não existe!");
            } catch (BusinessException e) {
                // TODO Auto-generated catch block
            }
        }
    }

    public void insert(Audiovisual audiovisual)
            throws AudiovisualInvalidException, DatabaseException, CloseConnectionException, BusinessException {

        audiovisualDAO.insert(audiovisual);
    }

    /**
     * Return an array filled with Audiovisual that matched with input.
     *
     * @param input
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     * @throws NoResultsException
     */
    public ArrayList<Audiovisual> search(String input)
            throws DatabaseException, CloseConnectionException, NoResultsException {

        ArrayList<Audiovisual> results = audiovisualDAO.search(input);

        if (results.isEmpty()) {
            throw new NoResultsException();
        }

        return results;
    }

    /**
     * Valid Rating of the series .
     *
     * @param audioVisual
     * @throws RatingInvalidException
     */
    public void validSerieRating(Audiovisual audioVisual) throws RatingInvalidException {
        // TODO 
        /*try {
           ValidateRatingService validade = new ValidateRatingService();
           validade.validRating(serie.getRating());
       } catch (RatingInvalidException r) {
           throw new RatingInvalidException();
       }*/
    }

    /**
     * Select all comments of serie.
     *
     * @param id
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public ArrayList<Rating> getRatings(int id) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations commentDAO = RatingDAO.getInstance();
        ArrayList<Rating> ratings = commentDAO.selectByAudiovisual(id);

        return ratings;
    }

    /**
     * Add a rating in database to related serie.
     *
     * @param rating
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public void addRating(Rating rating) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations ratingDAO = RatingDAO.getInstance();
        ratingDAO.insert(rating);
    }

    /**
     * Remove a rating in database.
     *
     * @param rating
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public void removeRating(Rating rating) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations ratingDAO = RatingDAO.getInstance();
        ratingDAO.delete(rating);
    }
}
