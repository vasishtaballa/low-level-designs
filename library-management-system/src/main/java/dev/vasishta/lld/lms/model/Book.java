package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.enums.BookCategory;
import dev.vasishta.lld.lms.enums.BookType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Book {
    private int id;
    private String isbn;
    private String title;
    private List<Author> authors;
    private int noOfPages;
    private BookCategory bookCategory;
    private BookType bookType;
}
