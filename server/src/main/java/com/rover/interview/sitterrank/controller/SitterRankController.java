package com.rover.interview.sitterrank.controller;

import com.rover.interview.sitterrank.model.Sitter;
import com.rover.interview.sitterrank.model.SitterComparator;
import com.rover.interview.sitterrank.dao.DatabaseClient;
import com.rover.interview.sitterrank.util.SitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/"})
public class SitterRankController {

    DatabaseClient databaseClient;
    SitterUtil sitterUtil;
    List<Sitter> sitters;

    @Autowired
    public SitterRankController(DatabaseClient databaseClient, SitterUtil sitterUtil) {
        this.databaseClient = databaseClient;
        this.sitterUtil = sitterUtil;
    }

    @RequestMapping(value = "/searchSitters", method = RequestMethod.GET)
    public final List<Sitter> searchSitters(@RequestParam int size) {
        initSitters();
        int endIndex = sitters.size() < size ? sitters.size() : size;
        return sitters.subList(0, endIndex);
    }

    @RequestMapping(value = "/filterByRating", method = RequestMethod.GET)
    public final List<Sitter> filterByRating(@RequestParam int greaterThan, int size) {
        initSitters();
        List<Sitter> result =
                sitters.stream().filter(
                        sitter -> sitter.getAvgRating() > greaterThan)
                        .collect(Collectors.toList());
        int endIndex = result.size() < size ? result.size() : size;
        return result.subList(0, endIndex);
    }

    void initSitters() {
        if(this.sitters == null) {
            this.sitters = databaseClient.getSitters();
            sitters.sort(new SitterComparator(sitterUtil));
        }
    }

}
