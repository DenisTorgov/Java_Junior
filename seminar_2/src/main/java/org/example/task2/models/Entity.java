package org.example.task2.models;


import org.example.task2.Column;

import java.util.UUID;

@org.example.task2.Entity
public class Entity {

    @Column(name = "id", primaryKey = true)
    private UUID id;

}
