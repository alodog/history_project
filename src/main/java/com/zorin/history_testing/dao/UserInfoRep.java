/**
 * @author Zorin Sergey
 */

package com.zorin.history_testing.dao;

import com.zorin.history_testing.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRep extends JpaRepository<UserInfo, Integer> {
    Iterable<UserInfo>findAllBySurname(String surname);
}
