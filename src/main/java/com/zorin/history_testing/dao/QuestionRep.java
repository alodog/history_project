/**
 * @author Zorin Sergey
 */

package com.zorin.history_testing.dao;

import com.zorin.history_testing.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRep extends CrudRepository<Question, Integer> {

}
