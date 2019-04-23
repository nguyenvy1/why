package com.rover.interview.sitterrank.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class Dog {
    Integer dogId;
    String dogName;
    Integer ownerId;
}
