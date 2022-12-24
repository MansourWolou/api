package com.example.demo.Content;

import com.example.demo.Content.Storage.StorageService;
import com.example.demo.user.UserModel;
import com.example.demo.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
     * @return
     */
    @GetMapping("contents")
    public ResponseEntity<List<ContentModel>> getAll(){
        List<ContentModel> contents = contentService.findAll();

        return ResponseEntity.ok(contents);
    }

    /**
     * retourne un content préci
     * @param id
     * @return
     */
    @GetMapping("content/{id}")
    public ResponseEntity<Optional<ContentModel>> getPersoneById(@PathVariable Integer id){
        Optional<ContentModel> content = contentService.findById(id);

        return ResponseEntity.ok(content);
    }

    /**
     * Ajouter un content
     * @return
     */
    @PostMapping("contents/add")
    public ResponseEntity<ContentModel> add(@RequestParam String type,
                                            @RequestParam String description,
                                            @RequestParam Integer userId,
                                            @RequestParam("file") MultipartFile file) throws IOException{
        Optional<UserModel> usr = userService.findById(userId);
        ContentModel cntent = new ContentModel(type,description,"g");
        usr.get().getContent().add(cntent);
        UserModel user = usr.get();
        //userService.save(user);
        ContentModel content = contentService.save( cntent);

        storageService.store(userId,file,content);
        return ResponseEntity.ok(content);
    }


    /**
     * Supprimer un content précis
     * @param id
     * @return
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
