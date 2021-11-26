/**
 * @author Zorin Sergey
 */

package com.zorin.history_testing.dao;

import com.zorin.history_testing.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRep extends JpaRepository<UserInfo, Integer> {
    UserInfo findBySurname(String surname);
    List<UserInfo>findAllBySurname(String surname);
}
