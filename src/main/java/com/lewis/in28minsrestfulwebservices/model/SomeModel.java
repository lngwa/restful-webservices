package com.lewis.in28minsrestfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonFilter("SomeModelFilter2")
public class SomeModel {
    private String field1;
    private String field2;
    private String field3;
}
