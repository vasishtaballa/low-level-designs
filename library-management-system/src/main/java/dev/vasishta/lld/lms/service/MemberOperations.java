package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.Book;

public interface MemberOperations {
    boolean reserveBook(Book book);

    boolean cancelReservation(Book book);

    boolean lendBook(Book book);

    boolean returnBook(Book book);

}
