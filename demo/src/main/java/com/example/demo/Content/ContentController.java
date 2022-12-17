package com.example.demo.Content;

import com.example.demo.user.UserModel;
import com.example.demo.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
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
     * @param name
     * @param surname
     * @return
     */
    @PostMapping("contents/add")
    public ResponseEntity<ContentModel> add(@RequestParam String name, @RequestParam String surname){

        ContentModel content = contentService.save(new ContentModel());
        return ResponseEntity.ok(content);
    }
    //TODO : uploadd de content
    /**
     * todo : faire une route pour l'objet content
     */
//    @PostMapping("contents/upload")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
////
////        storageService.store(file);
////        redirectAttributes.addFlashAttribute("message",
////                "You successfully uploaded " + file.getOriginalFilename() + "!");
////
//        return ResponseEntity.ok("redirect:/");
//    }


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
