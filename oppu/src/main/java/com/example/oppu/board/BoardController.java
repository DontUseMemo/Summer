package com.example.oppu.board;

import com.example.oppu.member.Member;
import com.example.oppu.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.metamodel.Bindable;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    //게시판 목록
    @RequestMapping("/boardList")
    public String list(Model model,
                       @PageableDefault(size = 10, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(value = "searchCate", required = false, defaultValue = "")String searchCate,
                       @RequestParam(value = "searchKeyword", required = false, defaultValue = "")String searchKeyword) {

        model.addAttribute("searchCate", searchCate);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("paging", boardService.getList(searchCate, searchKeyword, pageable));

        return "/board/boardList";
    }

//    @RequestMapping("/boardList")
//    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
////        List<Board> boardList = this.boardService.getList();
////        model.addAttribute("boardList", boardList);
//        Page<Board> paging = this.boardService.getList(page);
//        model.addAttribute("paging", paging);
//        return "/board/boardList";
//    }

    //게시판 상세보기
//    @RequestMapping(value = "/getBoard/{id}")
//    public String detail(Model model, @PathVariable("id") Long id) {
//        Board board = this.boardService.getBoardRequest(id);
//        model.addAttribute("board", board);
//        return "/board/getBoard";
//    }


    //게시판 새글쓰기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/insert")
    public String insertBoard() {
        return "/board/insertBoard";
    }

    //게시판 새글쓰기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/insert")
    public String insertBoard(@Valid BoardForm boardForm,
                              BindingResult bindingResult,
                              Principal principal,
                              @Nullable @RequestParam("uploadfile") MultipartFile[] uploadFile) {
        if (bindingResult.hasErrors()) {
            return "/board/insertBoard";
        }
        try {
//            this.boardService.insertBoard(category, title, nickname, content);


            Member member = this.memberService.getMember(principal.getName());
            Long board_id = this.boardService.insertBoard(boardForm.getTitle(), boardForm.getCategory(), boardForm.getContent(), member);

            //MultipartFile로 클라이언트에서 온 데이터가 무결성 조건에 성립을 안하거나 메타데이터가 없거나 문제가 새길 여지를 if문으로 처리
            for (MultipartFile file : uploadFile)
                if (!file.isEmpty()) {
                    FileUploadEntity entity = new FileUploadEntity(null,
                            UUID.randomUUID().toString(),
                            file.getContentType(),
                            file.getName(),
                            file.getOriginalFilename(),
                            board_id
                    );
                    //file업로드 테이블에 데이터 저장
                    boardService.insertFileUploadEntity(entity);
                    File newFileName = new File(entity.getUuid() + "_" + entity.getOriginalFileName());
                    //서버에 이미지 파일 저장
                    file.transferTo(newFileName);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Member member = this.memberService.getMember(principal.getName());
//        this.boardService.insertBoard(boardForm.getTitle(), boardForm.getCategory(), boardForm.getContent(), member);

        return "redirect:/board/boardList";
    }

    @RequestMapping(value = "/getBoard/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Board board = this.boardService.getBoardRequest(id);

        List<FileUploadEntity> fileUploadEntity = boardService.getFileuploadEntity2(board.getId());
        List<String> path = new ArrayList<>();
        for(FileUploadEntity fe : fileUploadEntity){
            String savePath = "/board/image/" + fe.getUuid()+"_"+fe.getOriginalFileName();
            path.add(savePath);
        }

        model.addAttribute("board", board);
        model.addAttribute("imgLoading",path);
        return "/board/getBoard";
    }

    @GetMapping(value = "/image/{imagename}")
    public ResponseEntity<byte[]> imageLoading(@PathVariable String imagename) throws IOException {
        // ResponseEntity<byte[]> : 메서드 리턴타입으로 이미기 데이터를 송신하기 위한 객체<바이트 배열>
        // throws IOExeception : 스트림방식으로 데이터를 전송하 ㄹ때 도중에 오류가 날 경우를 찾기 위해서 선언한 Exception

        String path = "C:\\\\Summer\\\\oppu\\\\src\\\\main\\\\resources\\\\static\\\\upload" + imagename;
        //File을 컴퓨터가 이해하기 위해서 Stream 배열을 만들어서 작업
        //객체(데이터 저장) : String , int ,double
        //Stream 객체는 파일을 컴퓨터가 cpu에서 바로 읽어들일 수 있도록 하는 객체
        FileInputStream fis = new FileInputStream(path);
        //Buffered : CPU에서 데이터를 읽어올 때 메모리와 캐시 사이에서 CPU와의 속도 차이를 줄이기 위한 중간 저장 위치
        BufferedInputStream bis = new BufferedInputStream(fis);
        //byte배열로 전환햐여 ResponseEntity를 통해 클라리언트에게 데이터 전달
        //HTTP프로토콜은 바이트 단위(배열)로 데이토를 주고 받음
        byte[] imgByteArr = bis.readAllBytes();
        //ResposeEntity를 통해 http프로톸놀로 클라이언트에게 데이터 전송
        return new ResponseEntity<byte[]>(imgByteArr, HttpStatus.OK);
    }
//
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/insert")
//    public String insertBoard(@RequestParam String category,
//                              @RequestParam String title,
//                              @RequestParam String content,
//                              Principal principal) {
//        Member member = this.memberService.getMember(principal.getName());
//        this.boardService.insertBoard(category, title, member, content);
//        return "redirect:/board/boardList";
//    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String updateBoard(Model model, BoardForm boardForm, @PathVariable("id") Long id, Principal principal) {
        Board board = this.boardService.getBoardRequest(id);
        if (!board.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        boardForm.setTitle(board.getTitle());
        boardForm.setContent(board.getContent());
        model.addAttribute("board", boardForm);
        return "/board/insertBoard";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String updateBoard(@Valid BoardForm boardForm, BindingResult bindingResult,
                              Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/board/insertBoard";
        }
        Board board = this.boardService.getBoardRequest(id);
        if (!board.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.boardService.modify(board, boardForm.getTitle(), boardForm.getCategory(), boardForm.getContent());
        return String.format("redirect:/board/getBoard/%s", id);
    }
}
