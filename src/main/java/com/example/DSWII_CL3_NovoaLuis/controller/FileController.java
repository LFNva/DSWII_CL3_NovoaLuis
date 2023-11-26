package com.example.DSWII_CL3_NovoaLuis.controller;

import com.example.DSWII_CL3_NovoaLuis.model.response.ResponseFile;
import com.example.DSWII_CL3_NovoaLuis.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"https://www.cibertec.edu.pe"})
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/file")
public class FileController {
    private FileService fileService;

    @PostMapping("/filesimages")
    public ResponseEntity<ResponseFile> subirPng(
            @RequestParam("files") MultipartFile files) throws Exception{
        fileService.guardarImagenes(files);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseFile.builder().message("Imagen Guardado Correctamente")
                                .build()
                );
    }
    @PostMapping("/filesexcel")
    public ResponseEntity<ResponseFile> subirDocumento(
            @RequestParam("files") MultipartFile files) throws Exception{
        fileService.guardarDocumentos(files);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        ResponseFile.builder().message("Documento Guardado Correctamente")
                                .build()
                );
    }
}
