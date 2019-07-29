package com.example.demo;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import org.omg.CORBA.RepositoryIdHelper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BoardRepository boardRepository){
        return args -> {
            ThreadLocalRandom.current().ints(100,10000).boxed().distinct().limit(10).forEach(x->{
                boardRepository.save(Board.builder()
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .title("강규한")
                        .content(x+"원")
                        .build());
            });
        };
    }
}
