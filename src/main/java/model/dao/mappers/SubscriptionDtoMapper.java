package model.dao.mappers;

import model.entity.DTO.SubscriptionDto;
import model.entity.Subscription;
import model.service.builders.SubscriptionDtoBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SubscriptionDtoMapper implements ObjectMapper<SubscriptionDto> {
    @Override
    public SubscriptionDto extractFromResultSet(ResultSet resultSet) throws SQLException {
        SubscriptionDtoBuilder builder = new SubscriptionDtoBuilder(resultSet.getInt("subscription.id"))
                .buildTotal(resultSet.getBigDecimal("bill"))
                .buildState(Subscription.StateEnum.valueOf(resultSet.getString("state").toUpperCase()))
                .buildStartDate(resultSet.getDate("start_date").toLocalDate())
                .buildEndDate(resultSet.getDate("end_date").toLocalDate())
                .buildTitleEn(resultSet.getString("title_en"))
                .buildTitleUa(resultSet.getString("title_ua"))
                .buildGenreEn(resultSet.getString("genre_en"))
                .buildGenreUa(resultSet.getString("genre_ua"))
                .buildPaymentDateTime(resultSet.getTimestamp("date_time_of_payment") == null ?
                        null : resultSet.getTimestamp("date_time_of_payment").toLocalDateTime())
                .buildOwnerId(resultSet.getInt("user_id"))
                .buildPublicationId(resultSet.getInt("publication.id"))
                .buildPaymentId(resultSet.getInt("payment.id"));
        return builder.build();
    }
}
