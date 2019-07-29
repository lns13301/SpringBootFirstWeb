package com.example.demo.domain;

import com.example.demo.repository.BoardRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Test {
    @Autowired
    private BoardRepository boardRepository;

    @Before
    public void init(){
        boardRepository.save(Board.builder().content("정주용").title("윤정도").createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now()).build());
    }

    @org.junit.Test
    public void test01(){

        assertThat(boardRepository.findBoardByTitle("윤정도").getContent()).isEqualTo("정주용");
    }

    @org.junit.Test
    public void test02(){
        Optional<Board> byId = boardRepository.findById(1L);
        assertThat(byId.orElse(null).getTitle()).isEqualTo("윤정도");
    }
}
