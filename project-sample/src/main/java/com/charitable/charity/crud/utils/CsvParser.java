package com.charitable.charity.crud.utils;


import com.mongodb.CommandResult;
import com.mongodb.MongoClient;
import org.apache.commons.io.FileUtils;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public static void main(String[] args) throws IOException {

        MongoClient mongoClient = new MongoClient();
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "charitable");
        MongoTemplate template = new MongoTemplate(mongoDbFactory);

        File file = new File("/Users/Magdalena/Desktop/charity-registry/extract_charity.csv");

        List<String> lines = FileUtils.readLines(file);
        ArrayList<String> errors = new ArrayList<>();

        System.out.println(file.getName());


        int count = 0;
        for (String line : lines) {
            if(count != 0){
                String[] split = line.split(",");
                try {

                    new VerifiedCharity(split[0], split[2]);
                    template.save(new VerifiedCharity(split[0], split[2]));
                } catch (Exception e){
                    errors.add(count + " " + line);
                }
            }
            count++;
        }

        FileUtils.writeLines(new File("parsing_errors.txt"), errors);

        CommandResult resultSet = template.getCollection("VerifiedCharity").getStats();

        System.out.println(resultSet.get("count"));

    }



}
