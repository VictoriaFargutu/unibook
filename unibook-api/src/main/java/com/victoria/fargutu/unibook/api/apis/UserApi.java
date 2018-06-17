package com.victoria.fargutu.unibook.api.apis;

import com.victoria.fargutu.unibook.repository.commons.UserRole;
import com.victoria.fargutu.unibook.repository.model.user.User;
import com.victoria.fargutu.unibook.service.security.HasRole;
import com.victoria.fargutu.unibook.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserApi {
    private UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @HasRole(UserRole.ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @HasRole(UserRole.ADMIN)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @HasRole(UserRole.USER)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}
