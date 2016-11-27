package com.example.user.achtung;

import java.util.*;

public class Player {
    private ArrayList<> scores;
    private int x, y;
    private Color color; //Have to be added to color
    private String name;

    public Player(String name) {
        this.scores = new ArrayList<>();
        this.name = name;
        this.x = 0;
        this.y = 0;


    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String getName() {
        return this.name;
    }

    public void addScore(int score) {
        this.scores.add(score);
    }

    public ArrayList<> getScore() {
        return this.scores;
    }

    public ArrayList<> getHighScore() {
        return Collections.sort(this.scores);
    }

    public int getDeaths() {
        return this.scores.size();
    }


}