package com.stonie.beans.impl;

import com.stonie.beans.TimeService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("timeService")
public class TimeServiceImpl implements TimeService {
    @Override
    public Date getCurrentTime() {
        return new Date();
    }
}
