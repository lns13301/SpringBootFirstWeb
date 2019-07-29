package com.example.demo.controller;

import com.example.demo.domain.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class BoardController{
    private BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Board> all = boardRepository.findAll();
        model.addAttribute("dataList", all);
        return "index";
    }

    @GetMapping("/add")
    public String add(Board board){
        return "add";
    }

    @PostMapping("/posting")
    public String posting(Board board, Model model){
        board.setCreatedDate(LocalDateTime.now());
        board.setUpdatedDate(LocalDateTime.now());
        boardRepository.save(board);
        List<Board> all = boardRepository.findAll();
        model.addAttribute("dataList", all);
        return "index";
    }

    @GetMapping("/{index}")
    public String boardIndex(@PathVariable Long index, Model model){
        Optional<Board> byId = boardRepository.findById(index);
        model.addAttribute("row",byId.orElse(null));
        return "page";
    }

    @GetMapping("/update/{index}")
    public String update(@PathVariable Long index, Model model){
        Optional<Board> byId = boardRepository.findById(index);
        model.addAttribute("row",byId.orElse(null));
        return "update";
    }

    @PostMapping("/updating/{index}")
    public String updating(@PathVariable Long index, Board board, Model model){
        board.setUpdatedDate(LocalDateTime.now());
        Board before = boardRepository.findById(index).orElse(null);
        board.setCreatedDate(before.getCreatedDate());


        boardRepository.save(board);

        List<Board> all = boardRepository.findAll();
        model.addAttribute("dataList", all);
        return "index";
    }

    @GetMapping("/delete/{index}")
    public String deleting(@PathVariable Long index, Model model){
        Board board = boardRepository.findById(index).orElse(null);
        boardRepository.delete(board);
        List<Board> all = boardRepository.findAll();
        model.addAttribute("dataList", all);
        return "index";
    }

}
