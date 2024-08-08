package org.choongang.file.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.choongang.file.entities.FileInfo;
import org.choongang.file.services.FileDeleteService;
import org.choongang.file.services.FileDownloadService;
import org.choongang.file.services.FileInfoService;
import org.choongang.file.services.FileUploadService;
import org.choongang.global.Utils;
import org.choongang.global.exceptions.BadRequestException;
import org.choongang.global.exceptions.RestExceptionProcessor;
import org.choongang.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController implements RestExceptionProcessor {
    //  RestExceptionProcessor : json 형태 오류도 같이 출력

    private final FileUploadService uploadService;
    private final FileDownloadService downloadService;
    private final FileInfoService infoService;
    private final FileDeleteService deleteService;
    private final Utils utils;

    @PostMapping("/upload")
    public ResponseEntity<JSONData> upload(@RequestPart("file") MultipartFile[] files,
                                         @Valid RequestUpload form, Errors errors) {

        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        List<FileInfo> items = uploadService.upload(files, form.getGid(), form.getLocation());
        HttpStatus status = HttpStatus.CREATED;
        JSONData data = new JSONData(items);
        data.setStatus(status);

        return ResponseEntity.status(status).body(data);
    }

    @GetMapping("/download/{seq}")
    public void download(@PathVariable("seq") Long seq) {
        downloadService.download(seq);

    }

    @DeleteMapping("/delete/{seq}")
    public JSONData delete(@PathVariable("seq") Long seq) {
        FileInfo data = deleteService.delete(seq);

        return new JSONData(data);
    }

    @DeleteMapping("/delete/{gid}")
    public JSONData delete(@PathVariable("gid") String gid, @RequestParam(name = "location", required = false) String location) {
        List<FileInfo> items = deleteService.delete(gid, location);

        return new JSONData(items);
    }

    @GetMapping("/info/{seq}")
    public JSONData get(@PathVariable("seq") Long seq) {
        FileInfo data = infoService.get(seq);
        return new JSONData(data);
    }

    // 목록 조회
    @GetMapping("/list/{gid}")
    public JSONData getList(@PathVariable("gid") String gid, @RequestParam(name = "location", required = false) String location) {
        List<FileInfo> items = infoService.getList(gid, location);

        return new JSONData(items);
    }
}
