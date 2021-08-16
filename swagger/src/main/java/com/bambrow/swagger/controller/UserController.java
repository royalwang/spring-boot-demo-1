package com.bambrow.swagger.controller;

import com.bambrow.swagger.dto.UserDTO;
import com.bambrow.swagger.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collection;

@Api(tags = {"User"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiIgnore
    @GetMapping("/")
    public String hello() {
        return "Hello world!";
    }

    @ApiOperation(value = "welcome", produces = MediaType.TEXT_PLAIN_VALUE, notes = "returns welcome message")
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome!";
    }

    @ApiOperation(value = "get user by id", produces = MediaType.TEXT_PLAIN_VALUE, notes = "returns user data")
    @GetMapping("/user/{id}")
    public String getUserById(@ApiParam(value = "user id", required = true, example = "1") @PathVariable("id") Long id) {
        UserDTO user = userService.get(id);
        return user == null ? "null" : user.toString();
    }

    @ApiOperation(value = "get all users", produces = MediaType.TEXT_PLAIN_VALUE, notes = "returns all user data")
    @GetMapping("/user/all")
    public String getAllUsers() {
        Collection<UserDTO> users = userService.getAll();
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        for (UserDTO user : users) {
            builder.append("  ").append(user.toString()).append(",\n");
        }
        builder.append("]");
        return builder.toString();
    }


    @ApiOperation(value = "set user", produces = MediaType.APPLICATION_JSON_VALUE, notes = "returns user id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "access_token", value = "access token", required = true, dataType = "string", paramType = "header", example = "abc"),
            @ApiImplicitParam(name = "id", value = "id", dataType = "string", paramType = "query", example = "123456")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "Request contains an invalid argument."),
            @ApiResponse(code = 500, message = "Interface exception.")
    })
    @PostMapping("/user/set")
    public Long setUser(@RequestBody UserDTO user) {
        userService.set(user.getId(), user);
        return user.getId();
    }

    @ApiOperation(value = "delete user by id", produces = MediaType.APPLICATION_JSON_VALUE, notes = "returns confirmation")
    @DeleteMapping("/user/delete")
    public boolean deleteUserById(@ApiParam(value = "user id", required = true, example = "1") @RequestParam("id") Long id) {
        return userService.delete(id);
    }

}
