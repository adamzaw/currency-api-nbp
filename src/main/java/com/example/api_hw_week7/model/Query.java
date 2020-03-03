package com.example.api_hw_week7.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table (name = "query")
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String query;

    public Query() {
    }

    public Query(String query) {
        this.query = query;
    }
}
