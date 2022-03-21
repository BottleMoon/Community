package me.project.community.service;

import me.project.community.domain.Board;
import me.project.community.dto.BoardRequestDto;
import me.project.community.dto.BoardResponseDto;
import me.project.community.repository.BoardsRepository;
import me.project.community.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final UsersRepository usersRepository;
    private final UsersService usersService;

    BoardsService(BoardsRepository boardsRepository, UsersRepository usersRepository, UsersService usersService) {
        this.boardsRepository = boardsRepository;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    public List<BoardResponseDto> findAll() {
        return boardsRepository.findAll().stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    public BoardResponseDto findBoardById(Long id) {
        Board board = boardsRepository.findById(id).get();
        return new BoardResponseDto(board);
    }

    public void createBoard(BoardRequestDto boardRequestDto) {
        Board board = dtoToEntity(boardRequestDto);
        boardsRepository.save(board);
    }

    public void patchBoard(BoardRequestDto boardRequestDto) {
        Board board = boardsRepository.findById(boardRequestDto.getId()).get();
        board = Board.builder()
                .id(boardRequestDto.getId())
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .user(board.getUser())
                .createdDate(board.getCreatedDate())
                .build();
        boardsRepository.save(board);
    }

    public Board dtoToEntity(BoardRequestDto boardRequestDto) {
        Board board = Board.builder()
                .id(boardRequestDto.getId())
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .user(usersRepository.getById(usersService.getSession()))
                .build();
        return board;
    }

}
