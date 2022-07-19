package com.example.beautysalon.controller;

import com.example.beautysalon.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PutMapping("/admin/deactivate-user-account/{id}")
    public ResponseEntity<HttpStatus> deactivateUserAccount(@PathVariable Long id) {
       adminService.deactivateAccount("regular_user", id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/deactivate-editor-account/{id}")
    public ResponseEntity<HttpStatus> deactivateEditorAccount(@PathVariable Long id) {
        adminService.deactivateAccount("editor", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/activate-editor-account/{id}")
    public ResponseEntity<String> activateEditorAccount(@PathVariable Long id) {
        adminService.activateAccount("editor", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/admin/activate-user-account/{id}")
    public ResponseEntity<String> activateUserAccount(@PathVariable Long id) {
        adminService.activateAccount("regular_user", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping("/admin/user-to-editor/{userId}")
//    public ResponseEntity<String> changeUserToEditor(@PathVariable Long userId) {
//        return adminService.changeUserToEditor(userId);
//    }
//
//    @PutMapping("/admin/editor-to-user/{userId}")
//    public ResponseEntity<String> changeEditorToUser(@PathVariable Long userId) {
//        return adminService.changeEditorToUser(userId);
//    }
}
