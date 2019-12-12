package myApp.protocol.loginLogout;

import myApp.protocol.AbstractPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginPayload extends AbstractPayload {
    private String email;
    private String password;
}
