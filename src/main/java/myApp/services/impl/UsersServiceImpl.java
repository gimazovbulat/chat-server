package myApp.services.impl;

import lombok.Getter;
import myApp.dao.repositories.UsersRepository;
import myApp.dto.Type;
import myApp.dto.UserDto;
import lombok.NoArgsConstructor;
import lombok.Setter;
import myApp.models.User;
import myApp.security.TokenResolver;
import myApp.services.interfaces.UsersService;
import myApp.servers.ClientHandler;

import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
public class UsersServiceImpl implements UsersService {
    UsersRepository usersRepository;

    public UserDto login(String email, String password, ClientHandler clientHandler) {
        Optional<User> optionalUser = usersRepository.checkLogin(email, password);
        if (optionalUser.isPresent()) {
            clientHandler.getChatMultiServer().getClients().add(clientHandler);
            String token = TokenResolver.createToken(optionalUser.get());
            return new UserDto(
                    Type.ONE,
                    optionalUser.get().getNickname(),
                    optionalUser.get().getEmail(),
                    optionalUser.get().getId(),
                    token
            );
        }
        throw new IllegalStateException();
    }

    public void logout(ClientHandler clientHandler) {
        clientHandler.getChatMultiServer().getClients().remove(clientHandler);
    }
}
