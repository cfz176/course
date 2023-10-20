package com.course.server.service;

import com.course.server.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.course.server.entity.Test;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
