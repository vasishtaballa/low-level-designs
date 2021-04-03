package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.Member;
import dev.vasishta.lld.lms.model.ReserveBook;

import java.util.List;

public interface ReservationService {
    boolean checkForAnyReservation(Book book);

    boolean reserveBook(Member member, Book book);

    List<ReserveBook> getReservations(Member member);
}
