package com.platform.cloud.xpmanagement;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.platform.cloud.xpmanagement.model.ExperienceLog;
import com.platform.cloud.xpmanagement.repository.ExperienceLogRepository;
import com.platform.cloud.xpmanagement.repository.ExperienceRepository;


@SpringBootTest
@AutoConfigureMockMvc
class XpmanagementApplicationTests {
	private static final Logger logger = LogManager.getLogger(XpmanagementApplicationTests.class);
	@Autowired
    private MockMvc mvc;

    @Autowired
    private ExperienceRepository experienceRepository;
    
    @AfterEach
    public void resetDb() {
    	experienceRepository.deleteAll();
    }
    
    @Test
    public void getExperinceTest() throws IOException, Exception {
    	mvc.perform(get("/experience/1").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.balance", is(0)));
    }
    
    @Test
    public void addExperinceTest() throws IOException, Exception {
        ExperienceLog exp = new ExperienceLog();
        exp.setPoints(2);
        exp.setCreatedAt(new Date(System.currentTimeMillis()));
        ExperienceLog exp2 = new ExperienceLog();
        exp2.setPoints(3);
        String user = "{\"points\": \"2\"}";
        String user2 = "{\"points\": \"3\"}";
        exp2.setCreatedAt(new Date(System.currentTimeMillis()));
        mvc.perform(post("/experience/2").contentType(MediaType.APPLICATION_JSON).content(user));
        mvc.perform(post("/experience/2").contentType(MediaType.APPLICATION_JSON).content(user2));

        mvc.perform(get("/experience/2").contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.balance", is(5)));
    }
    
    

}
