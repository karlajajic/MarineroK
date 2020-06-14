package com.example.marinero_kj.pojo;

public class Review {

    private String username;
    private String comment;

    public Review(String username, String comment){
        this.username=username;
        this.comment=comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString(){
        return username+": "+comment;
    }
}
