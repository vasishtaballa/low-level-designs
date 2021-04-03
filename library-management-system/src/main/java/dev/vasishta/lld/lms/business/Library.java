package dev.vasishta.lld.lms.business;

import dev.vasishta.lld.lms.model.Address;
import dev.vasishta.lld.lms.model.Catalog;
import dev.vasishta.lld.lms.model.Librarian;
import dev.vasishta.lld.lms.model.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Library {
    private int id;
    private String name;
    private Address address;
    private Date openedOn;
    private Catalog catalog;
    private List<Member> members;
    private List<Librarian> librarians;
}
