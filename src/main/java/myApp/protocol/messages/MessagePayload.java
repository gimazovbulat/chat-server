package myApp.protocol.messages;

import myApp.protocol.AbstractPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessagePayload extends AbstractPayload {
    private String text;
}
