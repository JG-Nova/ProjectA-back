package org.jgprojects.a.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.jgprojects.a.persistence.repository.StorageRepository;
import org.jgprojects.a.service.exception.StorageFileNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/files")
public class FileUploadController {
    private final StorageRepository storageRepository;

    public FileUploadController(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> listUploadedFiles() {
        List<String> list = storageRepository.loadAll().map(
            path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile",
            path.getFileName().toString()).build().toUri().toString()
        ).collect(Collectors.toList());
        
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageRepository.loadAsResource(filename);
        if(file == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"").body(file);
    }
    
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        storageRepository.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded "+ file.getOriginalFilename()+"!");

        return "redirect:/";
    }
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc){
        return ResponseEntity.notFound().build();
    }
}
