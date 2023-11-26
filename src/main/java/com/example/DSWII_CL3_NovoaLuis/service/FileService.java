package com.example.DSWII_CL3_NovoaLuis.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    private final Path rootFolderImagen = Paths.get("Images");
    private final Path rootFolderDocumento = Paths.get("Documentos");

    public void guardarImagenes(MultipartFile imagen) throws Exception{
        if(!imagen.getOriginalFilename().endsWith(".png")){
            throw new IllegalArgumentException("Solo se permiten archivos PNG");
        }
        Files.copy(imagen.getInputStream(), this.rootFolderImagen.resolve(imagen.getOriginalFilename()));
    }
    public void guardarDocumentos(MultipartFile documento) throws Exception{
        if(!documento.getOriginalFilename().endsWith(".xlsx")){
            throw new IllegalArgumentException("Solo se permiten archivos XLSX");
        }
        if(documento.getSize() > 1.5 * 1024 * 1024){ //1.5MB
            throw new IllegalArgumentException("El tamaño del archivo no puede superar 1.5 MB");
        }
        Files.copy(documento.getInputStream(), this.rootFolderDocumento.resolve(documento.getOriginalFilename()));
    }

}
