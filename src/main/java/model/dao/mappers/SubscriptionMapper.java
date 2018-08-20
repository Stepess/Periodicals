package model.dao.mappers;


import model.entity.Subscription;
import model.service.builders.SubscriptionBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SubscriptionMapper implements ObjectMapper<Subscription> {
    @Override
    public Subscription extractFromResultSet(ResultSet resultSet) throws SQLException {
        SubscriptionBuilder builder = new SubscriptionBuilder(resultSet.getInt("id"))
                .buildState(Subscription.StateEnum.valueOf(resultSet.getString("state").toUpperCase()))
                .buildDateOfStart(resultSet.getDate("start_date").toLocalDate())
                .buildDateOfEnd(resultSet.getDate("end_date").toLocalDate())
                .buildPublication(new PublicationMapper().extractFromResultSet(resultSet))
                .buildPayment(new PaymentMapper().extractFromResultSet(resultSet));
        return builder.build();
    }
}
