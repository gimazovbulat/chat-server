package myApp.services.impl;

import myApp.dto.HelpDto;
import lombok.NoArgsConstructor;
import myApp.dto.Type;
import myApp.services.interfaces.HelpService;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class HelpServiceImpl implements HelpService {

    public HelpDto help() {
        List<String> commands = Arrays.asList(
                "/login",
                "/logout",
                "/message",
                "/command" +
                        "\n----------\n" +
                        "get messages\n" +
                        "get products\n" +
                        "purchase\n" +
                        "remove product(admin)\n" +
                        "add product(admin)\n" +
                        "----------"
        );
        HelpDto helpDto = new HelpDto();
        helpDto.setHelpList(commands);
        helpDto.setType(Type.ONE);
        helpDto.setName("help");
        return helpDto;
    }
}
