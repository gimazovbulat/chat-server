package myApp.protocol.status;

import myApp.protocol.AbstractPayload;
import lombok.Data;

@Data
public class SuccessRespPayload extends AbstractPayload {
    private final String text = "Action successfully happened!";
}
