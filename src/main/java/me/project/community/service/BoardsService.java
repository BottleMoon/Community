package me.project.community.service;

import me.project.community.domain.Board;
import me.project.community.domain.Reply;
import me.project.community.dto.BoardRequestDto;
import me.project.community.dto.BoardResponseDto;
import me.project.community.dto.ReplyRequestDto;
import me.project.community.dto.ReplyResponseDto;
import me.project.community.error.ApiException;
import me.project.community.error.ApiExceptionAdvice;
import me.project.community.error.ExceptionEnum;
import me.project.community.repository.BoardsRepository;
import me.project.community.repository.RepliesRepository;
import me.project.community.repository.UsersRepository;
import me.project.community.session.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;
    private final UsersRepository usersRepository;
    private final RepliesRepository repliesRepository;
    private final UsersService usersService;
    private final UserInfo userInfo;

    BoardsService(BoardsRepository boardsRepository, UsersRepository usersRepository, RepliesRepository repliesRepository, UsersService usersService, UserInfo userInfo) {
        this.boardsRepository = boardsRepository;
        this.usersRepository = usersRepository;
        this.repliesRepository = repliesRepository;
        this.usersService = usersService;
        this.userInfo = userInfo;
    }

    public List<BoardResponseDto> findAll() {
        return boardsRepository.findAll().stream().map(BoardResponseDto::new).collect(Collectors.toList());
    }

    public BoardResponseDto findBoardById(Long id) {
        Board board = boardsRepository.findById(id).get();
        return new BoardResponseDto(board);
    }

    public void createBoard(BoardRequestDto boardRequestDto) {
        Board board = boardDtoToEntity(boardRequestDto);
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

    public Board boardDtoToEntity(BoardRequestDto boardRequestDto) {
        Board board = Board.builder()
                .id(boardRequestDto.getId())
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .user(usersRepository.getById(usersService.getSession()))
                .build();
        return board;
    }

    public ResponseEntity deleteBoard(Long id) {
        if (userInfo.getId().equals(boardsRepository.findById(id).get().getUser().getId())) {
            boardsRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return new ApiExceptionAdvice().exceptionHandler(new ApiException(ExceptionEnum.DELETE_FAIL_EXCEPTION));
        }
    }

    //reply

    public void postReply(Long boardId, ReplyRequestDto replyRequestDto) {
        Reply reply = replyDtoToEntity(replyRequestDto, boardId);
        repliesRepository.save(reply);
    }

    public Reply replyDtoToEntity(ReplyRequestDto replyRequestDto, Long boardId) {
        Reply reply = Reply.builder()
                .id(replyRequestDto.getId())
                .content(replyRequestDto.getContent())
                .createdTime(LocalDateTime.now())
                .board(boardsRepository.findById(boardId).get())
                .user(usersRepository.findById(userInfo.getId()).get())
                .build();
        return reply;
    }

    public List<ReplyResponseDto> getReply(Long boardId) {
        return repliesRepository.findAllByBoardId(boardId).stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }

}
