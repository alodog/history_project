/**
 * @author Zorin Sergey
 */

package com.zorin.history_testing.dao;

import com.zorin.history_testing.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultInfo extends JpaRepository<Result, Integer> {

}
