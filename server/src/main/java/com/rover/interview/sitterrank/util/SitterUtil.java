package com.rover.interview.sitterrank.util;

import com.rover.interview.sitterrank.model.Sitter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SitterUtil {

    public Float getOverallSitterRank(Sitter sitter) {
        Float sitterScore = calcSitterScore(sitter);
        Integer numSits = sitter.getNumSits();
        return sitterScore * sitterScoreWeight(numSits) + sitter.getAvgRating() * ratingWeight(numSits);
    }

    Float calcSitterScore(Sitter sitter) {
        String sitterName = sitter.getSitterName();
        Set<String> unique = new HashSet<>();
        for(int i = 0; i < sitterName.length(); i++) {
            Character character = sitterName.charAt(i);
            if(Character.isAlphabetic(sitterName.charAt(i))) {
                unique.add(character.toString().toLowerCase());
            }
        }
        return unique.size() / 26f * 5f;
    }

    Float sitterScoreWeight(Integer numSits) {
        if(numSits > 10) {
            return 0f;
        }
        return (10f - numSits) / 10f;
    }

    Float ratingWeight(Integer numSits) {
        if(numSits > 10) {
            return 1f;
        }
        return numSits / 10f;
    }

}
