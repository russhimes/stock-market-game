package com.techelevator.model;

import java.sql.Date;
import java.sql.Time;

public class Game {

    private int id;
    private String name;
    private int organizer_id;
    private Date end_date;
    private Time end_time;

    public Game(int id, String name, int organizer_id, Date end_date, Time end_time) {
        this.id = id;
        this.name = name;
        this.organizer_id = organizer_id;
        this.end_date = end_date;
        this.end_time = end_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(int organizer_id) {
        this.organizer_id = organizer_id;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }
}
