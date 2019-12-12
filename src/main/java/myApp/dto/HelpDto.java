package myApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HelpDto extends ResponseDto {
    private String name;
    private Type type;
    private List<String> helpList;

    public HelpDto(String name, Type type) {
        super(name, type);
    }
}
