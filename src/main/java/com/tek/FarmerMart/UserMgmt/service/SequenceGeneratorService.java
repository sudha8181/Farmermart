package com.tek.FarmerMart.UserMgmt.service;

import com.tek.FarmerMart.UserMgmt.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.sound.midi.Sequence;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoTemplate template;
    public long getNextSequence(String seqName){
        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true).upsert(true);

        Counter counter = template.findAndModify(query, update, options, Counter.class);
        return counter.getSeq();
    }
}


