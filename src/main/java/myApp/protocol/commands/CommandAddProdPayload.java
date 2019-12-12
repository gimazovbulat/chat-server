package myApp.protocol.commands;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommandAddProdPayload extends CommandPayload {
    private String productName;
    private float price;

    public CommandAddProdPayload(String command, String productName, float price) {
        super(command);
        this.productName = productName;
        this.price = price;
    }
}
