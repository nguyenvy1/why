package com.rover.interview.sitterrank.dao;

import com.rover.interview.sitterrank.model.Dog;
import com.rover.interview.sitterrank.model.Sitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseClient {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseClient(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrives a list of sitters for caching on the server.
     * @return List of sitters retrieved from the database
     */
    public List<Sitter> getSitters() {
        String sql =
                "SELECT * FROM SITTERS";

        return jdbcTemplate.query(sql, new SitterRowMapper());
    }

    /**
     * Initializes the DOGS table in the database based on the .csv reviews data. Called when application first
     * starts up.
     */
    public void initDogs() {
        List<Dog> dogs = getDogs();
        writeDogsToDB(dogs);
    }

    /**
     * Retrieves a list of the raw "dogs" column and corresponding owner IDs of those dogs from the database
     * and puts them into a list of Dogs.
     * @return List of dogs created from DB retrieval
     */
    List<Dog> getDogs() {
        String sql =
                "SELECT DISTINCT(DOGS) dogs, OWNERS.OWNER_ID owner_id FROM REVIEWS " +
                        "LEFT JOIN OWNERS " +
                        "ON OWNERS.OWNER_NAME = REVIEWS.OWNER;";
        List<Dog> dogs = new ArrayList<>();

        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        for(Map<String, Object> result : results) {
            String dogString = result.get("DOGS").toString();
            Integer ownerId = Integer.parseInt(result.get("OWNER_ID").toString());

            String[] dogNames = dogString.split("\\|");
            for(String dogName : dogNames) {
                Dog dog = new Dog();
                dog.setDogName(dogName);
                dog.setOwnerId(ownerId);
                dogs.add(dog);
            }
        }
        return dogs;
    }

    /**
     * Bulk inserts list of dogs into DOG table based on data derived from Reviews + Owners tables.
     * @param dogs List of dogs to write into database
     */
    void writeDogsToDB(List<Dog> dogs) {
        SimpleJdbcInsert dogInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("DOGS");
        dogInsert.executeBatch(mapDogs(dogs));
    }

    private MapSqlParameterSource[] mapDogs(List<Dog> dogs) {
        List<MapSqlParameterSource> entries = new ArrayList<>(dogs.size());
        for(Dog dog : dogs) {
            entries.add(new MapSqlParameterSource()
                    .addValue("dog_name", dog.getDogName())
                    .addValue("owner_id", dog.getOwnerId())
            );
        }
        return entries.stream().toArray(MapSqlParameterSource[] :: new);

    }

}
