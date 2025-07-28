package com.example.Matches.auth.controller;

import com.example.Matches.auth.dto.request.UserRequestDTO;
import com.example.Matches.auth.dto.request.UserRoleRequestDTO;
import com.example.Matches.auth.dto.request.UserUpdateRequestDto;
import com.example.Matches.auth.dto.response.CustomUserResponseDTO;
import com.example.Matches.auth.dto.response.UserResponseDto;
import com.example.Matches.auth.repository.UserRepo;
import com.example.Matches.auth.service.UserServiceIMPL;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping( "/User" )
public class UserController {

    private UserServiceIMPL userService;
    UserRepo userRepo;

    public UserController( UserServiceIMPL userService ,UserRepo userRepo ) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> create(@RequestBody UserRequestDTO requestDto) throws IOException {
        userService.create(requestDto);
        return ResponseEntity.ok("Successfully created user");
    }



    @GetMapping( "{id}" )
    public ResponseEntity<CustomUserResponseDTO> readOne(@PathVariable( "id" ) Long id ) {
        return ResponseEntity
                .ok()
                .body( userService.readOne( id ) );
    }

    @PostMapping( "change-roles" )
    public ResponseEntity<String> setUserRoles(@RequestBody UserRoleRequestDTO requestDTO ) {
        userService.setUserRoles( requestDTO ) ;
        return ResponseEntity.ok("Successfully set user roles");
    }
    @DeleteMapping
    public ResponseEntity<String>delete(@RequestParam Long id ) {
        userRepo.deleteById( id );
        return ResponseEntity.ok("Successfully deleted user");
    }

    @PutMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String>Update(@RequestParam Long id, @ModelAttribute UserUpdateRequestDto requestDTO ) throws IOException {
        userService.updateUser(id,requestDTO);
        return ResponseEntity.ok("Successfully updated user");
    }

    @GetMapping("search/{username}")
    public ResponseEntity<UserResponseDto> searchByUserName(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.searchByUsername(username));
    }

//    @MessageMapping("/user.addUser")
//    @SendTo("/user/public")
//    public User addUser(
//            @Payload User user
//    ) {
//        userService.saveActiveUser(user);
//        return user;
//    }

//    @MessageMapping("/user.disconnectUser")
//    @SendTo("/user/public")
//    public User disconnectUser(
//            @Payload User user
//    ) {
//        userService.disconnect(user);
//        return user;
//    }

//    @GetMapping("/users/connected")
//    public ResponseEntity<List<User>> findConnectedUsers() {
//        return ResponseEntity.ok(userService.findConnectedUsers());
//    }

}