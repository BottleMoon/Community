package me.project.community.service;

import me.project.community.domain.Board;
import me.project.community.dto.BoardRequestDto;
import me.project.community.dto.BoardResponseDto;
import me.project.community.repository.BoardsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;

    BoardsService(BoardsRepository boardsRepository) {
        this.boardsRepository = boardsRepository;
    }

    public List<BoardResponseDto> findAll() {
        return boardsRepository.findAll().stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    public BoardResponseDto findBoardById(Long id){
        Board board = boardsRepository.findById(id).get();
        return new BoardResponseDto(board);
    }

    public void createBoard(BoardRequestDto boardRequestDto) {
        Board board = Board.builder().boardRequestDto(boardRequestDto).build();
        boardsRepository.save(board);
    }
}
