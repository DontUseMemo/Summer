package com.example.oppu.board;

import com.example.oppu.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    //페이징 이전 게시글 리스트로 불러오기
//    public List<Board> getList() {
//        return this.boardRepository.findAll();
//    }


    //PathVariable id 사용하여 상세보기
    public Board getBoardRequest(Long id) {
        Optional<Board> question = this.boardRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    //새글 쓰기
    public void insertBoard(String category, String title, String nickname, String content) {
        Board board = new Board();
        board.setCategory(category);
        board.setTitle(title);
        board.setNickname(nickname);
        board.setContent(content);
        board.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(board);
    }

//    public List<Board> searchEmail(String boardSearch) {
//        return this.boardRepository.findBoardsByTitle(boardSearch);
//    }

    //페이징 된 리스트 불러오기
    //이후 검색기능 추가될 예정
    public Page<Board> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 2, Sort.by(sorts));
        return this.boardRepository.findAll(pageable);
    }
}
