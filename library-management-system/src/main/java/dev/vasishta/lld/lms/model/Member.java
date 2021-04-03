package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.enums.MembershipStatus;
import dev.vasishta.lld.lms.enums.ReservationStatus;
import dev.vasishta.lld.lms.exception.LMSException;
import dev.vasishta.lld.lms.service.BookLendingService;
import dev.vasishta.lld.lms.service.FineService;
import dev.vasishta.lld.lms.service.MemberOperations;
import dev.vasishta.lld.lms.service.ReservationService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Slf4j
public class Member extends Account implements MemberOperations {
    private MembershipStatus membershipStatus;
    private Date dateOfMembership;

    private FineService fineService;

    private BookLendingService bookLendingService;
    private ReservationService reservationService;

    @Override
    public boolean reserveBook(Book book) {
        if (reservationService.getReservations(this).size() >= 5)
            throw new LMSException("You can't reserve more than 5 books, Please cancel some other reservation and try again later");
        try {
            ReserveBook reserveBook = new ReserveBook();
            reserveBook.setReserveDate(new Date(System.currentTimeMillis()));
            reserveBook.setBook(book);
            reserveBook.setMember(this);
            reserveBook.setReservationStatus(ReservationStatus.CONFIRMED);
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
        ReserveBook bookObj = reserveBooks.stream()
                .filter(reserveBook -> (reserveBook.getBook().getIsbn() == book.getIsbn() && reserveBook.getReservationStatus() == ReservationStatus.CONFIRMED))
                .findAny()
                .get();
        if (bookObj == null)
            throw new LMSException("You have not reserved for this book. Please try with another book");
        return reserveBooks.remove(book);
    }

    @Override
    public boolean lendBook(Book book) {
        List<LendBook> lendBooks = bookLendingService.getLentBooks(this);
        if (lendBooks.size() >= 5)
            throw new LMSException("You can't lend more than 5 books at a time, Please return some other book to lend this book");
        Calendar calendar = Calendar.getInstance();
        LendBook lendBook = new LendBook();
        lendBook.setBook(book);
        lendBook.setMember(this);
        lendBook.setLentDate(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        lendBook.setDueDate(calendar.getTime());
        return lendBooks.add(lendBook);
    }

    @Override
    public boolean returnBook(Book book) {
        List<LendBook> lendBooks = bookLendingService.getLentBooks(this);
        if (lendBooks == null || lendBooks.isEmpty())
            throw new LMSException("You have not checked out any books to do this operation");
        LendBook lendBookObj = lendBooks.stream()
                .filter(lendBook -> (lendBook.getBook().getIsbn() == book.getIsbn()))
                .findAny()
                .get();
        // Check if current date is greater than due date. If so, collect fine
        if (lendBookObj.getDueDate().compareTo(Calendar.getInstance().getTime()) > 0)
            return calculateAndPayFine(lendBookObj);
        // Check if this book is reserved by anybody. If so, send notification to them that the book is available
        reservationService.checkForAnyReservation(book);
        return lendBooks.remove(lendBookObj);
    }

    private boolean calculateAndPayFine(LendBook lendBookObj) {
        Period period = Period.between(
                lendBookObj.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        double fineAmount = period.getDays() * fineService.FINE_PER_DAY;
        return fineService.collectFine(lendBookObj, fineAmount);
    }

}
