package dev.aomegax.echoboard.be.controller;


import dev.aomegax.echoboard.be.dto.request.EndUserReq;
import dev.aomegax.echoboard.be.model.EndUser;
import dev.aomegax.echoboard.be.service.EndUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/network")
@RequiredArgsConstructor
@Tag(name = "EndUser Network", description = "API to manage user network: peers, manager and other")
public class EndUserNetworkController {

    private final EndUserService endUserService;

    @PostMapping("/csv")
    public String uploadFile(@RequestParam("file") @NotEmpty MultipartFile file) {
        return endUserService.endUserNetwork(file);
    }


}