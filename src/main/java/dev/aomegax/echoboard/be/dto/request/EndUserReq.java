package dev.aomegax.echoboard.be.dto.request;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndUserReq {

    private String firstName;
    private String lastName;
    private String email;
}



