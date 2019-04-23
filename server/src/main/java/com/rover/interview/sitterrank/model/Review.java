package com.rover.interview.sitterrank.model;

import lombok.Data;

import java.util.Date;

@Data
public class Review {
    Integer reviewId;
    Integer rating;
    Date startDate;
    Date endDate;
    String text;
    String sitterName;
    String ownerName;
    Integer sitterId;
    Integer ownerId;
}
