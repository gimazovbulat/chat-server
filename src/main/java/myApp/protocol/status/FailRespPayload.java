package myApp.protocol.status;

import myApp.protocol.AbstractPayload;
import lombok.Data;

@Data
public class FailRespPayload extends AbstractPayload {
    private final String text = "You don't have enough authorities :(";
}
