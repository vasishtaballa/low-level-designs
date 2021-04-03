package dev.vasishta.lld.lms.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Catalog {
    private Map<String, List<BookItem>> booksByTitle;
    private Map<Author, List<BookItem>> booksByAuthor;
    private Map<Date, List<BookItem>> booksByPubDate;
}
