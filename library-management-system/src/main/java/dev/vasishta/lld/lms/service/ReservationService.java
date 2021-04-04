package dev.vasishta.lld.lms.service;

import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.Member;
import dev.vasishta.lld.lms.model.ReserveBook;

import java.util.List;

public interface ReservationService {

    Member checkForAnyReservation(Book book);

    boolean reserveBook(Member member, Book book);

    List<ReserveBook> getReservations(Member member);

    boolean cancelReservation(Member member, Book book);
}
