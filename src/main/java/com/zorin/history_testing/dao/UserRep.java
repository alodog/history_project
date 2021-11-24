/**
 * @author Zorin Sergey
 */

package com.zorin.history_testing.dao;

import com.zorin.history_testing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Integer> {
    User findByUsername(String userName);
}
