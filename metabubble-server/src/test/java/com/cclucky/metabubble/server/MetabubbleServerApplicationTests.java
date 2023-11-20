package com.cclucky.metabubble.server;

import com.cclucky.metabubble.server.service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MetabubbleServerApplicationTests {

    @Autowired
    ScheduleService scheduleService;

    @Test
    void contextLoads() {
        scheduleService.saveDataToDatabase();
    }

}
