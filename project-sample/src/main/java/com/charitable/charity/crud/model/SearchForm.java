package com.charitable.charity.crud.model;

public class SearchForm {

    private String customQuery;


    public SearchForm(){}

    public SearchForm(String customQuery) {
        this.customQuery = customQuery;
    }

    public String getCustomQuery() {
        return customQuery;
    }

    public void setCustomQuery(String customQuery) {
        this.customQuery = customQuery;
    }
}
