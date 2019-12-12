package myApp.protocol.commands;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommandPurchasePayload extends CommandPayload {
    private String productName;

    public CommandPurchasePayload(String command, String productName) {
        super(command);
        this.productName = productName;
    }

}
