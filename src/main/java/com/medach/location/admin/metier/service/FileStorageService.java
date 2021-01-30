package com.medach.location.admin.metier.service;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {


    public void save(MultipartFile file,String repository);

    public Resource load(String filename,String root);

    public void deleteAll(Path root);

    public void delete(File f);

    public Stream<Path> loadAll(Path root);
}
