package com.mario.myapplication.responses;

import com.mario.myapplication.model.Audioguide;
import com.mario.myapplication.model.Category;
import com.mario.myapplication.model.Description;

public class PoiResponse {

    private String _id;
    private String name;
    private Category[] categories;
    private CoordinatesResponse loc;
    private String[] comments;
    private float stars;
    private String qrCode;
    private Audioguide audioguides;
    private Description description;
    private String coverImage;
    private String[] images;
    private int year;
    private String creator;
    private String status;
    private String schedule;
    private float price;

    public PoiResponse() {}

    public PoiResponse(String id, String name, Category[] categories, CoordinatesResponse loc, String[] comments, float stars, String qrCode, Audioguide audioguides, Description description, String coverImage, String[] images, int year, String status, String schedule) {
        this._id = id;
        this.name = name;
        this.categories = categories;
        this.loc = loc;
        this.comments = comments;
        this.stars = stars;
        this.qrCode = qrCode;
        this.audioguides = audioguides;
        this.description = description;
        this.coverImage = coverImage;
        this.images = images;
        this.year = year;
        this.status = status;
        this.schedule = schedule;
    }

    public PoiResponse(String id, String name, Category[] categories, CoordinatesResponse loc, String[] comments, float stars, String qrCode, Audioguide audioguides, Description description, String coverImage, String[] images, int year, String creator, String status, String schedule, float price) {
        this._id = id;
        this.name = name;
        this.categories = categories;
        this.loc = loc;
        this.comments = comments;
        this.stars = stars;
        this.qrCode = qrCode;
        this.audioguides = audioguides;
        this.description = description;
        this.coverImage = coverImage;
        this.images = images;
        this.year = year;
        this.creator = creator;
        this.status = status;
        this.schedule = schedule;
        this.price = price;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public CoordinatesResponse getloc() {
        return loc;
    }

    public void setloc(CoordinatesResponse loc) {
        this.loc = loc;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Audioguide getAudioguides() {
        return audioguides;
    }

    public void setAudioguides(Audioguide audioguides) {
        this.audioguides = audioguides;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
