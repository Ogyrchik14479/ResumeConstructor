package igor.reznikov.LearnJUnit.controllers;

import igor.reznikov.LearnJUnit.dtos.UserDto;
import igor.reznikov.LearnJUnit.mappers.UserMapper;
import igor.reznikov.LearnJUnit.servecies.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userMapper.userToUserDto(userService.getUser(id));
    }

}