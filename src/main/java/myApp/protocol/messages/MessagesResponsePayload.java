package myApp.protocol.messages;

import myApp.protocol.AbstractPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.models.Message;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessagesResponsePayload extends AbstractPayload {
    private List<Message> messages;
}
