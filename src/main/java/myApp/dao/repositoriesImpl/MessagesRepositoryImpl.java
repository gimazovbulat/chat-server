package myApp.dao.repositoriesImpl;

import lombok.NoArgsConstructor;
import lombok.Setter;
import myApp.dao.RowMappper;
import myApp.dao.repositories.MessagesRepository;
import myApp.models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class MessagesRepositoryImpl implements MessagesRepository {
    @Setter
    private Connection connection;
    private RowMappper<Message> rowMappper = row -> {
        Integer id = row.getInt("id");
        String nickname = row.getString("nickname");
        String text = row.getString("text");
        Date date = new Date(row.getTimestamp("date").getTime());
        return Message.builder()
                .id(id)
                .text(text)
                .nickname(nickname)
                .date(date)
                .build();
    };

    public MessagesRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public void saveMessage(Message message) {
        String sql = "INSERT INTO javalab_scheme.messages2 (user_id, text, date) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            int userId = message.getUserId();
            String text = message.getText();
            long date = message.getDate().getTime();
            ps.setInt(1, userId);
            ps.setString(2, text);
            ps.setTimestamp(3, new Timestamp(date));
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<Message> getMessages(int size, int page) {
        String sql =
                "SELECT m2.id, nickname, text, date " +
                        "FROM javalab_scheme.messages2 m2 JOIN javalab_scheme.users3 u3 " +
                        "ON m2.user_id = u3.id " +
                        "ORDER BY date LIMIT ? OFFSET ?";
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                messages.add(rowMappper.maprow(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        if (messages.size() != 0) return messages;
        return Collections.emptyList();
    }
}
