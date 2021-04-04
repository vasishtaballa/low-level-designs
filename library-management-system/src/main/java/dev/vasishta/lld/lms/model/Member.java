package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.enums.MembershipStatus;
import dev.vasishta.lld.lms.enums.ReservationStatus;
import dev.vasishta.lld.lms.exception.LMSException;
import dev.vasishta.lld.lms.service.BookLendingService;
import dev.vasishta.lld.lms.service.MemberOperations;
import dev.vasishta.lld.lms.service.ReservationService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Slf4j
public class Member extends Account implements MemberOperations {
    private MembershipStatus membershipStatus;
    private Date dateOfMembership;

    private BookLendingService bookLendingService;
    private ReservationService reservationService;

    @Override
    public boolean reserveBook(Book book) {
        if (reservationService.getReservations(this).size() >= 5)
            throw new LMSException("You can't reserve more than 5 books, Please cancel some other reservation and try again later");
        try {
            reservationService.reserveBook(this, book);
        } catch (Exception ex) {
            throw new LMSException("Exception occurred while reserving a book");
        }
        return true;
    }

    @Override
    public boolean cancelReservation(Book book) {
        List<ReserveBook> reserveBooks = reservationService.getReservations(this);
        if (reserveBooks == null || reserveBooks.isEmpty())
            throw new LMSException("You have no active reservations");
        return reservationService.cancelReservation(this, book);
    }

    @Override
    public boolean lendBook(Book book) {
        List<LendBook> lendBooks = bookLendingService.getLentBooks(this);
        if (lendBooks.size() >= 5)
            throw new LMSException("You can't lend more than 5 books at a time, Please return some other book to lend this book");
        return bookLendingService.lendBook(this, book);
    }

    @Override
    public boolean returnBook(Book book) {
        List<LendBook> lendBooks = bookLendingService.getLentBooks(this);
        if (lendBooks == null || lendBooks.isEmpty())
            throw new LMSException("You have not checked out any books to do this operation");
        return bookLendingService.returnBook(this, book);
    }
}
