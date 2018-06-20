package com.imd.telemaco.entity;

import java.util.ArrayList;

import com.imd.telemaco.entity.enums.Classification;

/**
 * This class represents a Audiovisual object. Where its fields are id, name,
 * status - if it is finished or at progress, synopsis and image - which in real
 * is the system path image.
 *
 * @author Shirley Ohara Telemaco de Freitas (shirleyohara@ufrn.edu.br)
 * @version 4 de jun de 2018 | 10:29:31
 */
public abstract class Audiovisual {

    private int id;
    private String name;
    private String status;
    private String synopsis;
    private String image;
    private Classification classification;
    private ArrayList<Rating> ratings;

    /**
     * Default Constructor
     */
    public Audiovisual() {
    }

    /**
     * Parameterized Constructor
     *
     * @param name
     * @param status
     * @param synopsis
     * @param image
     */
    public Audiovisual(String name, String status, String synopsis, String image, Classification classification) {
        this.name = name;
        this.status = status;
        this.synopsis = synopsis;
        this.image = image;
        this.classification = classification;
        this.ratings = new ArrayList<>();
    }

    /**
     * Parameterized Constructor by the id
     *
     * @param id
     * @param name
     * @param status
     * @param synopsis
     * @param image
     */
    public Audiovisual(int id, String name, String status, String synopsis, String image, Classification classification, ArrayList<Rating> ratings) {
        this(name, status, synopsis, image, classification);
        this.id = id;
        this.ratings = ratings;
    }

    /**
     * Returns the id of the Audiovisual
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the Audiovisual
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the status of the Audiovisual
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the synopsis of the Audiovisual
     *
     * @return synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Returns the image (path) of the Audiovisual
     *
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Returns the classification of the Audiovisual
     *
     * @return classification
     */
    public Classification getClassification() {
        return classification;
    }

    /**
     * Returns the ratings of the Audiovisual
     *
     * @return ratings
     */
    public ArrayList<Rating> getRating() {
        return ratings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }

    /**
     * Returns the classification of the Audiovisual in String type
     *
     * @return cString;
     */
    public String classifToString() {
        String cString;
        switch (classification) {
            case GENERAL:
                cString = "L";
                break;
            case P10:
                cString = "10";
                break;
            case P12:
                cString = "12";
                break;
            case P14:
                cString = "14";
                break;
            case P16:
                cString = "16";
                break;
            default:
                cString = "18";
        }
        return cString;
    }

    /**
     * Convert a String type to Classification type
     *
     * @param classif
     * @return classification
     */
    public Classification stringToClassif(String classif) {
        Classification classification;

        switch (classif) {
            case "L":
                classification = Classification.GENERAL;
                break;
            case "10":
                classification = Classification.P10;
                break;
            case "12":
                classification = Classification.P12;
                break;
            case "14":
                classification = Classification.P14;
                break;
            case "16":
                classification = Classification.P16;
                break;
            default:
                classification = Classification.P18;
                break;

        }

        return classification;
    }

    public void setName(String name) {
        this.name = name;
    }
}
