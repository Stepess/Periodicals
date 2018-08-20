package model.dao.jdbc;

import model.dao.SubscriptionDao;
import model.entity.BookedPublication;
import model.entity.Subscription;
import model.service.resource.manager.DataBaseManager;
import model.service.resource.manager.ResourceManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcSubscriptionDao implements SubscriptionDao {
    private DataSource source;
    private ResourceManager manager;

    public JdbcSubscriptionDao(DataSource source) {
        this.source = source;
        this.manager = new DataBaseManager();
    }

    @Override
    public boolean setInDb(Subscription entity) {
        int result=0;
        try (
                Connection connection = source.getConnection();
        ) {
            connection.setAutoCommit(false);

            StringBuilder pubsQuery = new StringBuilder(manager.getProperty("db.subscription.query.set.pubs"));
            List<BookedPublication> list = entity.getPublicationList();
            for (BookedPublication publication: list) {
                pubsQuery.append("(?, ?, ?, ?)");
                //pubsQuery
            }

            try (
                    PreparedStatement statementSub = connection.prepareStatement(manager.getProperty("db.subscription.query.set.subs"));
                    PreparedStatement statementPubs = connection.prepareStatement(manager.getProperty(""))
                    ) {


                statementSub.setFloat(1,entity.getPayment().getBill().floatValue());
                statementSub.setString(2,entity.getPayment().getState().toString().toLowerCase());
                //statementSub.setTimestamp(3,entity.getPayment().getDateTimeOfPayment());
                statementSub.setInt(4,entity.getOwnerId());

                result = statementSub.executeUpdate();

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    @Override
    public Subscription getById(int id) {
        return null;
    }

    @Override
    public List<Subscription> getAll() {
        return null;
    }

    @Override
    public boolean update(Subscription entity) {
        return false;
    }

    @Override
    public boolean delete(Subscription entity) {
        return false;
    }

    @Override
    public void close() {

    }
}
