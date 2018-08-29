package model.dao.mappers;

import model.entity.DTO.PublicationDto;
import model.entity.DTO.SubscriptionDto;
import model.entity.Publication;
import model.entity.Subscription;
import model.service.builders.SubscriptionBuilder;
import model.service.builders.SubscriptionDtoBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SubscriptionDtoMapper implements ObjectMapper<SubscriptionDto> {

    @Override
    public SubscriptionDto extractFromResultSet(ResultSet resultSet) throws SQLException {
        PublicationDTOMapper publicationDTOMapper = new PublicationDTOMapper();
        SubscriptionDtoBuilder builder = new SubscriptionDtoBuilder(resultSet.getInt("subscription.id"))
                .buildTotal(resultSet.getBigDecimal("total"))
                .buildState(Subscription.StateEnum.valueOf(resultSet.getString("state").toUpperCase()))
                .buildStartDate(resultSet.getDate("start_date").toLocalDate())
                .buildEndDate(resultSet.getDate("end_date").toLocalDate())
                .buildTitleEn(resultSet.getString("title_en"))
                .buildTitleUa(resultSet.getString("title_ua"))
                .buildPaymentDateTime(resultSet.getTimestamp("date_time_of_payment") == null ?
                        null :
                        resultSet.getTimestamp("date_time_of_payment").toLocalDateTime())
                //.buildPublicationDto(publicationDTOMapper.extractFromResultSet(resultSet))
                .buildOwnerId(resultSet.getInt("user_id"));
        return builder.build();
    }


}
