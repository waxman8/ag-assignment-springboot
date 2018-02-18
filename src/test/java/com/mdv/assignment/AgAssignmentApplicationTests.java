package com.mdv.assignment;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgAssignmentApplicationTests {
    
                
        @Autowired
        private Simulator simulator;
        
	/**
         * unit test to ensure that the tweet repository is created when a new simulator is created
         */
        @Test
        public void tweetRepositoryCreated() {
            assertThat(simulator.getTweetRepo().toString()).isNotNull();
        }
        
        /**
         * unit test to ensure that the user repository is created when a new simulator is created
         */
        @Test
        public void userRepositoryCreated() {
            assertThat(simulator.getUserRepo().toString()).isNotNull();
        }
        
        @Test
	public void contextLoads() {
	}

        
}
