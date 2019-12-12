package myApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionStatusDto extends ResponseDto{
   private String text;

    public ActionStatusDto(Type type, String text, String name) {
        super(name, type);
        this.text = text;
    }
}
