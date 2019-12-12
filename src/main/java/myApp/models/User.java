package myApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private Integer id;
    private String nickname;
    private Role role;

    public static class Role {
        private String userRole;

        public String getUserRole() {
            return userRole;
        }

        public Role(String userRole) {
            this.userRole = userRole;
        }
    }
}
