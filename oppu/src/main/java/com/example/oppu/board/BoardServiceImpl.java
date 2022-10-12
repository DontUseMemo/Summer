package com.example.oppu.board;

import com.example.oppu.DataNotFoundException;
import com.example.oppu.member.Member;
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
    @Override
    public Board getBoardRequest(Long id) {
        Optional<Board> board = this.boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    //새글 쓰기
    @Override
    public void insertBoard(String title, String category, String content, Member member) {
        Board board = new Board();
        board.setTitle(title);
        board.setCategory(category);
        board.setContent(content);
        board.setWriter(member);
        board.setCreateDate(LocalDateTime.now());
        this.boardRepository.save(board);
    }

    //게시글 목록보기
    //이후 검색기능 추가될 예정(10월 11일 Controller에 추가)
    @Override
    public Page<Board> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.boardRepository.findAll(pageable);
    }


    //게시글 카테고리, 검색하여 목록보기
    @Override
    public Page<Board> getList(String searchCate, String searchKeyword, Pageable pageable) {

        Page<Board> boards = null;

        if (searchCate.equals("title")) {
            boards = boardRepository.findByTitleContaining(searchKeyword, pageable);
        } else if (searchCate.equals("writer")) {
            boards = boardRepository.findByWriterContaining(searchKeyword, pageable);
        } else if (searchCate.equals("content")) {
            boards = boardRepository.findByContentContaining(searchKeyword, pageable);
        } else {
            boards = boardRepository.findAll(pageable);
        }

        return boards;
    }

    public void modify(Board board, String title, String category, String content) {
        board.setTitle(title);
        board.setContent(content);
        board.setCategory(category);
        board.setUpdateDate(LocalDateTime.now());
        this.boardRepository.save(board);
    }
}
