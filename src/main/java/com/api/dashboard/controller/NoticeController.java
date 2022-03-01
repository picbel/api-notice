package com.api.dashboard.controller;

import com.api.dashboard.model.NoticeDTO;
import com.api.dashboard.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("")
    public ResponseEntity<?> register(
            @RequestBody @Valid NoticeDTO noticeDTO){

        return new ResponseEntity<>(new NoticeDTO(noticeService.register(noticeDTO)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modify(
            @PathVariable Long id,
            @RequestBody NoticeDTO noticeDTO) {

            noticeDTO.setId(id);
        return new ResponseEntity<>(new NoticeDTO(noticeService.modify(noticeDTO)), HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
            ){

        return new ResponseEntity<>(noticeService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> view(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(new NoticeDTO(noticeService.view(id)), HttpStatus.OK);
    }

}
