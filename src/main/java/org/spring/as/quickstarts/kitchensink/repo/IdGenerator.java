package org.spring.as.quickstarts.kitchensink.repo;

import org.spring.as.quickstarts.kitchensink.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Long getNextSequence(String collectionName) {
        Query query = new Query().addCriteria(Criteria.where("id").is(collectionName));
        Update update = new Update().inc("seq", 1);
        Counter counter = mongoTemplate.findAndModify(query, update, Counter.class);

        if (counter == null) {
            counter = new Counter();
            counter.setId(collectionName);
            counter.setSeq(1L);  // Start with 1
            mongoTemplate.save(counter);
        }

        return counter.getSeq();
    }
}
