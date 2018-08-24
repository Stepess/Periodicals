package model.dao.mappers;


import model.entity.Publication;
import model.entity.Subscription;
import model.service.builders.SubscriptionBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class SubscriptionMapper implements ObjectMapper<Subscription> {
    @Override
    public Subscription extractFromResultSet(ResultSet resultSet) throws SQLException {
        PublicationMapper publicationMapper = new PublicationMapper();
        Map<Integer, Publication> publications = new HashMap<>();
        SubscriptionBuilder builder = new SubscriptionBuilder(resultSet.getInt("id"))
                .buildTotal(resultSet.getBigDecimal("total"))
                .buildState(Subscription.StateEnum.valueOf(resultSet.getString("state").toUpperCase()))
                .buildStartDate(resultSet.getDate("start_date").toLocalDate())
                .buildEndDate(resultSet.getDate("end_date").toLocalDate())
                .buildPublication(publicationMapper.makeUnique(publications,
                        publicationMapper.extractFromResultSet(resultSet)))
                .buildPayment(new PaymentMapper().extractFromResultSet(resultSet))
                .buildOwnerId(resultSet.getInt("user_id"));
        return builder.build();
    }
}
