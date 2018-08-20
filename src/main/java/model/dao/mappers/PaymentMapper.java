package model.dao.mappers;

import model.entity.Payment;
import model.service.builders.PaymentBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements ObjectMapper<Payment> {
    @Override
    public Payment extractFromResultSet(ResultSet resultSet) throws SQLException {
        PaymentBuilder builder = new PaymentBuilder(resultSet.getInt("id"))
                .buildBill(resultSet.getBigDecimal("bill"))
                .buildDateTimeOFPayment(resultSet.getTimestamp("date_time_of_payment").toLocalDateTime());
        return builder.build();
    }
}