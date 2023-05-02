package com.edic.azureadeval.models;

import java.util.List;

public class Results {
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

    public Results(String username) {
        this.username = username;
    }
    public Results() {
    }



}
