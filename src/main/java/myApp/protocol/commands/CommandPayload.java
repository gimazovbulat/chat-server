package myApp.protocol.commands;

import myApp.protocol.AbstractPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CommandPayload extends AbstractPayload {
    private String command;
}
