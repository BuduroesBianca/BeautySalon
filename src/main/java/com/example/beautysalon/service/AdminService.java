package com.example.beautysalon.service;

public interface AdminService {

    public void deactivateAccount(String role, Long id);

    public void activateAccount(String role, Long id) ;

//    public ResponseEntity<String> changeUserToEditor(Long id);
//
//    public ResponseEntity<String> changeEditorToUser(Long id);
//    public ResponseEntity<HttpStatus> changeUserRoles(Long userId, Long roleId) throws UserRoleNotFoundException, AccountNotFoundException;
}
