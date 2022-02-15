package com.lewis.in28minsrestfulwebservices.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int id;
    private int userId;
    private String message;
    private Date created;
}
