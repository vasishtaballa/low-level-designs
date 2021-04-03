package dev.vasishta.lld.lms.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Address {
    private int id;
    private String line1;
    private String line2;
    private String line3;
    private String city;
    private String state;
    private String country;
    private int pinCode;
    private String phoneCode;
    private String phoneNumber;
}
