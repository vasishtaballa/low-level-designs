package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.Author;
import dev.vasishta.lld.lms.model.BookItem;

import java.util.Date;
import java.util.List;

public interface SearchService {
    List<BookItem> searchBookByTitle(String title, boolean onlyAvailable);

    List<BookItem> searchBookByAuthor(Author author, boolean onlyAvailable);

    List<BookItem> searchBookByPubDate(Date pubDate, boolean onlyAvailable);
}
