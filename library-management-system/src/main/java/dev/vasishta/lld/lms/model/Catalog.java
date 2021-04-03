package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.exception.LMSException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@Slf4j
public class Catalog {
    private Map<String, List<BookItem>> booksByTitle;
    private Map<Author, List<BookItem>> booksByAuthor;
    private Map<Date, List<BookItem>> booksByPubDate;

    // All helper methods to update catalog
    public boolean addBackToCatalog(BookItem book) {
        return addBackToCatalogTitleMap(book, booksByTitle) &&
                addBackToCatalogAuthorMap(book, booksByAuthor) &&
                addBackToCatalogDateMap(book, booksByPubDate);
    }

    public boolean addBackToCatalogTitleMap(BookItem book, Map<String, List<BookItem>> map) {
        try {
            if (book != null) {
                if (map.containsKey(book.getTitle()))
                    map.get(book.getTitle()).add(book);
                else {
                    List<BookItem> books = new ArrayList<>();
                    books.add(book);
                    map.put(book.getTitle(), books);
                }
            }
        } catch (Exception ex) {
            log.error("Exception occurred while putting book back to catalog: {} with map: {}", book, map);
            throw new LMSException("Exception occurred while putting book back to catalog");
        }
        return true;
    }

    public boolean addBackToCatalogAuthorMap(BookItem book, Map<Author, List<BookItem>> map) {
        try {
            if (book != null) {
                List<Author> authors = book.getAuthors();
                authors.forEach(author -> {
                    if (map.containsKey(author))
                        map.get(author).add(book);
                    else {
                        List<BookItem> books = new ArrayList<>();
                        books.add(book);
                        map.put(author, books);
                    }
                });
            }
        } catch (Exception ex) {
            log.error("Exception occurred while putting book back to catalog: {} with map: {}", book, map);
            throw new LMSException("Exception occurred while putting book back to catalog");
        }
        return true;
    }

    public boolean addBackToCatalogDateMap(BookItem bookItem, Map<Date, List<BookItem>> map) {
        try {
            if (bookItem != null) {
                if (map.containsKey(bookItem.getPublicationDate()))
                    map.get(bookItem.getPublicationDate()).add(bookItem);
                else {
                    List<BookItem> books = new ArrayList<>();
                    books.add(bookItem);
                    map.put(bookItem.getPublicationDate(), books);
                }
            }
        } catch (Exception ex) {
            log.error("Exception occurred while putting bookItem back to catalog: {} with map: {}", bookItem, map);
            throw new LMSException("Exception occurred while putting bookItem back to catalog");
        }
        return true;
    }

    public boolean removeFromCatalog(BookItem bookItem) {
        // TODO: Impl this method
        return true;
    }
}
