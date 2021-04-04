package dev.vasishta.lld.lms.service.impl;

import dev.vasishta.lld.lms.exception.LMSException;
import dev.vasishta.lld.lms.model.Book;
import dev.vasishta.lld.lms.model.LendBook;
import dev.vasishta.lld.lms.model.Member;
import dev.vasishta.lld.lms.service.BookLendingService;
import dev.vasishta.lld.lms.service.FineService;
import dev.vasishta.lld.lms.service.NotificationService;
import dev.vasishta.lld.lms.service.ReservationService;

import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class BookLendingServiceImpl implements BookLendingService {

    private Map<Member, List<LendBook>> memToLendMap;

    private Map<LendBook, Member> lendToMemMap;

    private FineService fineService;

    private ReservationService reservationService;

    private NotificationService notificationService;

    @Override
    public boolean lendBook(Member member, Book book) {
        Calendar calendar = Calendar.getInstance();
        LendBook lendBook = new LendBook();
        lendBook.setBook(book);
        lendBook.setMember(member);
        lendBook.setLentDate(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 10);
        lendBook.setDueDate(calendar.getTime());
        updateMaps(member, lendBook);
        return true;
    }

    @Override
    public List<LendBook> getLentBooks(Member member) {
        if (memToLendMap.containsKey(member))
            return memToLendMap.get(member);
        throw new LMSException("You have not lent any books");
    }

    @Override
    public boolean returnBook(Member member, Book book) {
        List<LendBook> lendBooks = getLentBooks(member);
        LendBook lendBookObj = lendBooks.stream()
                .filter(lendBook -> (lendBook.getBook().getIsbn() == book.getIsbn()))
                .findAny()
                .get();
        // Check if current date is greater than due date. If so, collect fine
        if (lendBookObj.getDueDate().compareTo(Calendar.getInstance().getTime()) > 0)
            return calculateAndPayFine(lendBookObj);
        // Check if this book is reserved by anybody. If so, send notification to them that the book is available
        Member reservationMember = reservationService.checkForAnyReservation(book);
        if (reservationMember != null)
            notificationService.sendNotification(reservationMember, book);
        return lendBooks.remove(lendBookObj);
    }

    private void updateMaps(Member member, LendBook lendBook) {
        if (memToLendMap.containsKey(member))
            memToLendMap.get(member).add(lendBook);
        else {
            List<LendBook> lendBooks = new ArrayList<>();
            lendBooks.add(lendBook);
            memToLendMap.put(member, lendBooks);
        }
        if (lendToMemMap.containsKey(lendBook))
            throw new LMSException("This item is already lent by somebody. Please try with some other item");
        else
            lendToMemMap.put(lendBook, member);
    }

    private boolean calculateAndPayFine(LendBook lendBookObj) {
        Period period = Period.between(
                lendBookObj.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        double fineAmount = period.getDays() * fineService.FINE_PER_DAY;
        return fineService.collectFine(lendBookObj, fineAmount);
    }
}
