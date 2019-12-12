package myApp.protocol.commands;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class CommandListPayload extends CommandPayload {
    private int page;
    private int size;

    public CommandListPayload(String command, int page, int size) {
        super(command);
        this.page = page;
        this.size = size;
    }

}
