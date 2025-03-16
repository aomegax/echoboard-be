package dev.aomegax.echoboard.be.controller;


import dev.aomegax.echoboard.be.dto.request.EndUserReq;
import dev.aomegax.echoboard.be.model.EndUser;
import dev.aomegax.echoboard.be.service.EndUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Tag(name = "EndUser", description = "API to manage users")
public class EndUserController {

    private final EndUserService endUserService;

    @PostMapping
    public EndUser createUser(@RequestBody EndUserReq user) {
        return endUserService.saveUser(user);
    }

    @GetMapping
    public List<EndUser> getAllUsers() {
        return endUserService.getAllEndUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        endUserService.deleteUser(id);
    }
}