package myApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResponseDto {
    String name;
    Type type;

    public ResponseDto(String name, Type type) {
        this.name = name;
        this.type = type;
    }
}
