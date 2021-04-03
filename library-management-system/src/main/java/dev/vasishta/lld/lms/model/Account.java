package dev.vasishta.lld.lms.model;

import dev.vasishta.lld.lms.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private Address address;
    private AccountStatus accountStatus;
}
