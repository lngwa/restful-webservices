package com.lewis.in28minsrestfulwebservices.model;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    @Size(min = 2, message = "Name must have atleast two characters")
    private String name;
    private Date dob;
}
