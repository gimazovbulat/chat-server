package myApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private int id;
    private String text;
    private Date date;
    private int userId;
    private String nickname;
    private List<Message> messages;
    private List<Product> products;

    public Message(String text, Date date, int userId) {
        this.text = text;
        this.date = date;
        this.userId = userId;
    }

    public Message(String text, Date date, String nickname) {
        this.text = text;
        this.date = date;
        this.nickname = nickname;
    }

}
