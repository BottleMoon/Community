package me.project.community.controller;

import me.project.community.dto.BoardRequestDto;
import me.project.community.dto.BoardResponseDto;
import me.project.community.service.BoardsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("boards")
public class BoardsController {

        private final BoardsService boardsService;

        BoardsController(BoardsService boardsService){
            this.boardsService = boardsService;
        }


    @GetMapping("")
    public List<BoardResponseDto> findAll(){
        return boardsService.findAll();
    }

    @GetMapping("/{id}")
    public BoardResponseDto findBoardById(@PathVariable Long id){
        return boardsService.findBoardById(id);
    }

    @PostMapping("")
    public void createBoard(@RequestBody BoardRequestDto boardRequestDto){
            boardsService.createBoard(boardRequestDto);
    }
}
