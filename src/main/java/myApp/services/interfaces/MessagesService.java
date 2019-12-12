package myApp.services.interfaces;

import myApp.config.Component;
import myApp.dao.repositories.MessagesRepository;
import myApp.dto.MessageDto;

public interface MessagesService extends Component {
    MessageDto message(String token, String text);
    MessagesRepository getMessagesRepository();
}
