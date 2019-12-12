package myApp.protocol.commands;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommandRemoveProdPayload extends CommandPayload{
    private String productName;

    public CommandRemoveProdPayload(String command, String productName) {
        super(command);
        this.productName = productName;
    }
}
