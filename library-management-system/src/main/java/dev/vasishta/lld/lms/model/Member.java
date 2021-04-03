package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.enums.MembershipStatus;
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
public class Member extends Account {
    private MembershipStatus membershipStatus;
    private Date dateOfMembership;
    private List<LendBook> lendBooks;

    public boolean reserveBook() {
        return false;
    }

    public boolean lendBook() {
        return false;
    }

    public boolean returnBook() {
        return true;
    }
}
