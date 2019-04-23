package com.rover.interview.sitterrank.model;

import lombok.Data;

@Data
public class Sitter {
    Integer sitterId;
    String sitterName;
    String sitterImage;
    String sitterEmail;
    String sitterPhoneNumber;
    Float avgRating;
    Integer numSits;
}
