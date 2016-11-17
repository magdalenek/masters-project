package com.charitable.charity.crud.controller;

import com.charitable.charity.crud.model.Note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public NoteController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @RequestMapping(method = RequestMethod.POST)
    public Map<String, Object> createNote(@RequestBody Note note) throws UnknownHostException {

        Map<String, Object> response = new LinkedHashMap<>();
        mongoTemplate.save(note);
        response.put("message", "Note created successfully");
        return response;
    }

    //Get by id
    @RequestMapping(method = RequestMethod.GET, value = "/{charityId}")
    public Note getAdminNote(@PathVariable("charityId") String id) {

        Query findingNote = new Query(Criteria.where("id").is(id));
        Note note = mongoTemplate.findOne(findingNote, Note.class);

        return note;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Map<String, Object> getAllNotes() {
        List<Note> allNotes = mongoTemplate.findAll(Note.class);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("totalNotes", allNotes.size());
        response.put("notes", allNotes);
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class NotFoundException extends RuntimeException {
    }

}
