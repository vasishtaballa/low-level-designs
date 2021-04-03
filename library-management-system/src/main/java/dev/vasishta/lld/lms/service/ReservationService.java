package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.Member;

public interface ReservationService {
    boolean checkForAnyReservation(Book book);

    boolean reserveBook(Member member, Book book);
}
