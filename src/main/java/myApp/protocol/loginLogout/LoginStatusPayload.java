package myApp.protocol.loginLogout;

import myApp.protocol.AbstractPayload;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginStatusPayload extends AbstractPayload {
    @Getter
    private String text = "Welcome!";
}
