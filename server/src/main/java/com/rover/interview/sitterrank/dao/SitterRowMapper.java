package com.rover.interview.sitterrank.dao;

import com.rover.interview.sitterrank.model.Sitter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SitterRowMapper implements RowMapper<Sitter> {
    @Override
    public Sitter mapRow(ResultSet rs, int rownumber) throws SQLException {
        Sitter sitter = new Sitter();
        sitter.setNumSits(rs.getInt("num_sits"));
        sitter.setAvgRating(rs.getFloat("avg_rating"));
        sitter.setSitterEmail(rs.getString("sitter_email"));
        sitter.setSitterImage(rs.getString("sitter_image"));
        sitter.setSitterId(rs.getInt("sitter_id"));
        sitter.setSitterPhoneNumber(rs.getString("sitter_phone_number"));
        sitter.setSitterName(rs.getString("sitter_name"));

        return sitter;
    }
}
