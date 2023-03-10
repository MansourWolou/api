package com.example.demo.Content;

import com.example.demo.Content.Storage.StorageService;
import com.example.demo.user.UserModel;
import com.example.demo.user.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api")
public class ContentController {

    private final ContentService contentService;
    private final StorageService storageService;
    private final UserService userService;

    public ContentController(ContentService contentService, StorageService storageService, UserService userService) {
        this.contentService = contentService;
        this.storageService = storageService;
        this.userService = userService;
    }

    /**
     * retourner une liste de touts les content
     * TODO: ecrire test
     * @return
     */
    @GetMapping("contents")
    public ResponseEntity<List<ContentModel>> getAll(){
        List<ContentModel> contents = contentService.findAll();

        return ResponseEntity.ok(contents);
    }

    /**
     * retourne un content préci
     * TODO: ecrire test
     * @param id
     * @return
     */

    @GetMapping("content/{id}")
    public ResponseEntity<Optional<ContentModel>> getContentById(@PathVariable Integer id){
        Optional<ContentModel> content = contentService.findById(id);

        return ResponseEntity.ok(content);
    }

    /**
     *
     * @param type
     * @param description
     * @param userId
     * @param file
     * @return
     * @throws IOException
     * TODO: Gérer les exception si il ya
     * TODO: le path enregistrer dans la bdd n'es pas bien , trouver mieux
     * TODO: ecrire test
     */
    @PostMapping("contents/add")
    public ResponseEntity<ContentModel> add(@RequestParam String type,
                                            @RequestParam String description,
                                            @RequestParam Integer userId,
                                            @RequestParam("file") MultipartFile file) throws IOException
    {
        Optional<UserModel> usr = userService.findById(userId);
        ContentModel cntent = new ContentModel(type,description);
        String path = UUID.randomUUID()
                + "-" + file.getOriginalFilename();
        storageService.uploadFile(file,path);

        cntent.setPath(path);
        usr.get().getContent().add(cntent);// Rajoute la clé étrangère
        ContentModel content = contentService.save( cntent);

        return ResponseEntity.ok(content);
    }

    @GetMapping("content/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadContent(@PathVariable String fileName) throws IOException {
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    /**
     * Supprimer un content précis
     * @param id
     * @return
     * TODO: ecrire test
     */
    @DeleteMapping("contents/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        contentService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    /**
     * Mettre a jour un content
     * @param content
     * @return
     */
    @PostMapping("contents/update")
    public ResponseEntity<String> update(@RequestBody ContentModel content){
        contentService.save(content);
        return ResponseEntity.ok("updated");
    }


}
