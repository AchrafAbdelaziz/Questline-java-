/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Review {
    private int id;
    private String comment;
    private int rating;
    private int id_util_id;

    public Review() {
        
    }

    public Review(String comment, int rating, int id_util_id) {
        this.comment = comment;
        this.rating = rating;
        this.id_util_id = id_util_id;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Review(int id, String comment, int rating) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
    }

    public Review(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
    }

    public int getId_util_id() {
        return id_util_id;
    }

    public void setId_util_id(int id_util_id) {
        this.id_util_id = id_util_id;
    }

    @Override
    public String toString() {
        return "Review{" + "id=" + id + ", comment=" + comment + ", rating=" + rating + '}';
    }
    
}
