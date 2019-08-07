package ua.training.admission.model.dao.mapper;

import ua.training.admission.model.entity.Message;
import ua.training.admission.view.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Message Mapper
 *
 * @author Oleksii Morenets
 */
public class MessageMapper extends ObjectMapper<Message> {

    @Override
    public Message extractFromResultSet(ResultSet resultSet) throws SQLException {
        Message message = null;
        long userId = resultSet.getLong(SQL.MESSAGE_USER_ID);
        if (!resultSet.wasNull()) {
            message = Message.builder()
                    .averageGrade(resultSet.getDouble(SQL.MESSAGE_AVERAGE_GRADE))
                    .message(resultSet.getString(SQL.MESSAGE_MESSAGE))
                    .messageRead(resultSet.getBoolean(SQL.MESSAGE_MESSAGE_READ))
                    .build();
            message = makeUnique(message, userId);
        }

        return message;
    }
}
