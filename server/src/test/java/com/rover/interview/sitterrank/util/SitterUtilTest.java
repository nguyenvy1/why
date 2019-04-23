package com.rover.interview.sitterrank.util;

import com.rover.interview.sitterrank.model.Sitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SitterUtilTest {

    Sitter testSitter;
    SitterUtil sitterUtil = new SitterUtil();

    @BeforeEach
    void setUp() {
        Sitter sitter = new Sitter();
        sitter.setSitterPhoneNumber("777-777-7777");
        sitter.setSitterImage("dog.jpg");
        sitter.setSitterEmail("dog@dog.com");
        sitter.setNumSits(5);
        sitter.setAvgRating(5f);
        sitter.setSitterName("AbcdefGhIjkPu");
        sitter.setSitterId(1);

        testSitter = sitter;
    }

    @Test
    void test_getOverallSitterRank_lessThan10Sits() {
        Float sitterScore = sitterUtil.getOverallSitterRank(testSitter);
        assertEquals(Float.valueOf(3.75f), sitterScore);
    }

    @Test
    void test_getOverallSitterRank_moreThan10Sits() {
        testSitter.setNumSits(11);
        Float sitterScore = sitterUtil.getOverallSitterRank(testSitter);
        assertEquals(Float.valueOf(5f), sitterScore);
    }
}