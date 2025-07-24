package com.example.Matches.auth.service;

import com.example.Matches.auth.dto.request.UserRequestDTO;
import com.example.Matches.auth.dto.request.UserRoleRequestDTO;
import com.example.Matches.auth.dto.request.UserUpdateRequestDto;
import com.example.Matches.auth.dto.response.CustomUserResponseDTO;
import com.example.Matches.auth.dto.response.UserResponseDto;
import com.example.Matches.auth.model.Role;
import com.example.Matches.auth.model.User;
import com.example.Matches.auth.repository.RoleRepo;
import com.example.Matches.auth.repository.UserRepo;
import com.example.Matches.config.image.CloudneryImageService;
import com.example.Matches.config.notification.SSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service

public class UserServiceIMPL implements UserService {

    private UserRepo userRepository;
    private  PasswordEncoder passwordEncoder;
    private RoleRepo roleRepository;
    private SSEService<User> userSSEService;

    @Autowired
    private CloudneryImageService cloudneryImageService;

   public UserServiceIMPL(UserRepo userRepo,PasswordEncoder passwordEncoder,RoleRepo roleRepository,SSEService<User> userSSEService) {
       this.userRepository = userRepo;
       this.passwordEncoder = passwordEncoder;
       this.roleRepository = roleRepository;
       this.userSSEService = userSSEService;
   }


   public User ConvertToEntity(User user, UserRequestDTO userRequestDTO) throws IOException {

       user.setUsername( userRequestDTO.getUsername() );
       user.setEmail( userRequestDTO.getEmail() );
       user.setPassword( passwordEncoder.encode(userRequestDTO.getPassword() ));

       return user;
   }




    public void create(UserRequestDTO requestDto) throws IOException {
       User user1=userRepository.findByUsername(requestDto.getUsername());
       if(user1!=null){
           throw new RuntimeException("User already exists");
       }

       User user = ConvertToEntity(new User(), requestDto);

       userRepository.save(user);

       userSSEService.emit( user );

    }


    public CustomUserResponseDTO readOne(Long id ) {
        CustomUserResponseDTO singleUserById = userRepository.findUserByUserId(id);
        if ( Objects.isNull( singleUserById ) ) {
            throw new RuntimeException( "User with id " + id + " not found." );
        }
        return singleUserById;
    }


    public User setUserRoles( UserRoleRequestDTO requestDTO ) {

        User foundUser = userRepository.findById( requestDTO.userId() ).get();

        if ( Objects.isNull( foundUser ) ) {
            throw new RuntimeException( "User with id " + requestDTO.userId() + " not found." );
        }

        Set<Role> foundRoles = roleRepository.findAllByIdIn( requestDTO.roleIds() );
        foundUser.getRoles().addAll( foundRoles );

        return  userRepository.save( foundUser );

    }


    @Override
    public void updateUser(Long id, UserUpdateRequestDto userRequestDTO) throws IOException {

       User user=userRepository.findById( id ).get();

       User updateUser = ConvertToEntityUpdate(user, userRequestDTO);

       userRepository.save( updateUser );

    }

    @Override
    public UserResponseDto searchByUsername(String username) {
        return userRepository.searchByUsername( username );
    }

    public User ConvertToEntityUpdate(User user,UserUpdateRequestDto userRequestDTO) throws IOException {
//
//        Map<String, Object> heroUploadResult = cloudneryImageService.upload(profilepic);
//        String profileImageUrl = (String) heroUploadResult.get("secure_url");

        return user;
    }





//    public List<User> findConnectedUsers() {
//        return userRepository.findAllByStatus(Status.ONLINE);
//    }


}
