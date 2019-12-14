package ua.training.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SendFileResponse {
    private boolean success;
    private String fileName;
    private String email;
}