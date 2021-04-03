package dev.vasishta.lld.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
}
