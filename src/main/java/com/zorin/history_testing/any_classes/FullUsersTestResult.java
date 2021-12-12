package com.zorin.history_testing.any_classes;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zorin Sergey
 */

@Component
@Data
public class FullUsersTestResult {

    private List<String> answers=new ArrayList<>();
    private String rating;

    public FullUsersTestResult() {
    }

}
