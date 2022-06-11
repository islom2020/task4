package com.example.task4.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class EditUsersRequest {

    // selected users ids that should be edited
    @NotEmpty(message = "Id list cannot be empty.")
    private Long[] ids;

    // block, unblock, delete -> selected users
    @NotBlank(message = "Non of actions selected")
    private String action;
}
