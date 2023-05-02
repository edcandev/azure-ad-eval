package com.edic.azureadeval.models;

import org.json.JSONObject;

import java.util.List;

public class Evaluation {
    @Override
    public String toString() {
        return "Evaluation{" +
                "username='" + username + '\'' +
                ", ranges=" + ranges +
                ", total=" + total +
                '}';
    }

    private String username;
    private List<Ranges> ranges;
    private Double total;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Ranges> getRanges() {
        return ranges;
    }

    public void setRanges(List<Ranges> ranges) {
        this.ranges = ranges;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Evaluation(String username, List<Ranges> ranges, Double total) {
        this.username = username;
        this.ranges = ranges;
        this.total = total;
    }

    public Evaluation() {
    }

}
