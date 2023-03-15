package com.example.demo.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("users")
    public ResponseEntity<List<UserModel>> getAll(){
        List<UserModel> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Optional<UserModel>> getPersoneById(@PathVariable Integer id){
        Optional<UserModel> user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping("users/add")
    public ResponseEntity<UserModel> add(@RequestParam String name, @RequestParam String surname){

        UserModel user = userService.save(new UserModel());
        return ResponseEntity.ok(user);
    }



    @DeleteMapping("users/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        userService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("users/update")
    public ResponseEntity<String> update(@RequestBody UserModel user){
        userService.save(user);
        return ResponseEntity.ok("updated");
    }

// FRONT
//
//    @GetMapping("users/list")
//    public String showUpdateForm(Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }
//    @PostMapping("users/addUser")
//    public String addUser(@Valid UserModel user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "add-user";
//        }
//
//        userService.save(user);
//        return "redirect:list";
//    }
//
//    @GetMapping("users/edit/{id}")
//    public String edit(@PathVariable("id") Integer id, Model model) {
//        UserModel user = userService.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        model.addAttribute("user", user);
//        return "update-user";
//    }
//
//    @GetMapping("users/view/{id}")
//    public String view(@PathVariable("id") Integer id, Model model) {
//        UserModel user = userService.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        model.addAttribute("user", user);
//        return "view-user";
//    }
//
//    @PostMapping("users/update/{id}")
//    public String updateUser(@PathVariable("id") Integer id, @Valid UserModel user, BindingResult result,
//                             Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "index";
//        }
//
//        userService.save(user);
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }
//    @GetMapping("users/delete/{id}")
//    public String deleteUser(@PathVariable("id") Integer id, Model model) {
//        UserModel user = userService.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        userService.delete(user);
//        model.addAttribute("users", userService.findAll());
//        return "index";
//    }
//
//    @GetMapping("users/signup")
//    public String showSignUpForm(UserModel user) {
//        return "add-user";
//    }
//



}

