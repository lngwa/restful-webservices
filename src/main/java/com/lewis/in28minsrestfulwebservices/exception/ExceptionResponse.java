package com.lewis.in28minsrestfulwebservices.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private int errorCode;
}
