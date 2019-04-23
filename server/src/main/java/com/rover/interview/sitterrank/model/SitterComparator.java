package com.rover.interview.sitterrank.model;

import com.rover.interview.sitterrank.util.SitterUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;

public class SitterComparator implements Comparator<Sitter> {

    SitterUtil sitterUtil;

    @Autowired
    public SitterComparator(SitterUtil sitterUtil) {
        this.sitterUtil = sitterUtil;
    }

    @Override
    public int compare(Sitter o1, Sitter o2) {
        Float sitterRank1 = sitterUtil.getOverallSitterRank(o1);
        Float sitterRank2 = sitterUtil.getOverallSitterRank(o2);
        return Float.compare(sitterRank2, sitterRank1);
    }

}
