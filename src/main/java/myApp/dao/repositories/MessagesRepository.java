package myApp.dao.repositories;

import myApp.config.Component;
import myApp.models.Message;

import java.sql.Connection;
import java.util.List;

public interface MessagesRepository extends Component {
    void saveMessage(Message message);
    List<Message> getMessages(int size, int page);
    void setConnection(Connection connection);
}
