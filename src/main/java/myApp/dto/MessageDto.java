package myApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MessageDto extends ResponseDto {
    String text;
    Date date;
    String senderNickname;

    public MessageDto(Type type, String text, Date date, String senderNickname) {
        super("message", type);
        this.text = text;
        this.date = date;
        this.senderNickname = senderNickname;
    }



}
