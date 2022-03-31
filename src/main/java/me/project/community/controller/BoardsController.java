package me.project.community.controller;

import me.project.community.dto.BoardRequestDto;
import me.project.community.dto.BoardResponseDto;
import me.project.community.dto.ReplyRequestDto;
import me.project.community.dto.ReplyResponseDto;
import me.project.community.service.BoardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("boards")
public class BoardsController {

    private final BoardsService boardsService;

    BoardsController(BoardsService boardsService) {
        this.boardsService = boardsService;
    }

    //boards

    @GetMapping("")
    public List<BoardResponseDto> findAll() {
        return boardsService.findAll();
    }

    @GetMapping("/{id}")
    public BoardResponseDto findBoardById(@PathVariable Long id) {
        return boardsService.findBoardById(id);
    }

    @PostMapping("")
    public void createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        boardsService.createBoard(boardRequestDto);
    }

    @PatchMapping("/{id}")
    public void patchBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        boardsService.patchBoard(boardRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable Long id) {
        return boardsService.deleteBoard(id);
    }


    //replies
    @PostMapping("/replies/{boardId}")
    public void postReply(@PathVariable Long boardId, @RequestBody ReplyRequestDto replyRequestDto) {
        boardsService.postReply(boardId, replyRequestDto);
    }

    @GetMapping("/replies/{boardId}")
    public List<ReplyResponseDto> getReply(@PathVariable Long boardId) {
        return boardsService.getReply(boardId);
    }

}
