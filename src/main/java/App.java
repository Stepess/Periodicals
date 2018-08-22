import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.SubscriptionDao;
import model.dao.UserDao;
import model.dao.jdbc.JdbcDaoFactory;
import model.entity.Publication;
import model.entity.Subscription;
import model.entity.User;
import model.service.builders.PublicationBuilder;
import model.service.builders.SubscriptionBuilder;
import model.service.builders.UserBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        DaoFactory factory = new JdbcDaoFactory();
        /*UserDao dao = factory.createUserDao();
        System.out.println(dao.getAll());

        User user = new UserBuilder(9)
                 .buildLogin("Jedi")
                .buildPassword("asdasdsad")
                .buildEmail("email@email.com")
                .buildRole(User.RoleEnum.USER)
                .buildFirstName("Obi-Wan")
                .buildLastName("Kenobi")
                .buildAddress("Far-far galaxy")
                .buildAccount(BigDecimal.valueOf(54664.21))
                .build();


        user.setNationalField("firstName", "Обі-Ван");
        user.setNationalField("lastName", "Кенобі");
        user.setNationalField("address", "Далека-далека галактика");
        user.setPassword("may0the0force");


        System.out.println(dao.setInDb(user));
        System.out.println(dao.getAll());

        user.setNationalField("address", "Дуже далека-далека галактика");

        System.out.println(dao.update(user));
        System.out.println(dao.getAll());
        System.out.println(dao.delete(user));
        System.out.println(dao.getAll());*/

       /* PublicationDao dao = factory.createPublicationDao();

        System.out.println(dao.getAll());



        Publication publication = new PublicationBuilder(5,"title", "genre")
                .buildDescription("description")
                .buildAuthor("authror")
                .buildPrice(BigDecimal.valueOf(6554.25))
                .build();

        System.out.println(dao.setInDb(publication));

        System.out.println(dao.getAll());
*/


        SubscriptionDao dao = factory.createSubscriptionDao();
        PublicationDao publicationDao = factory.createPublicationDao();

        List<Subscription> list = dao.getAll();

        list.forEach(System.out::println);

        Subscription subscription = new SubscriptionBuilder(5)
                .buildStartDate(LocalDate.now())
                .buildEndDate(LocalDate.now().plusMonths(2))
                .buildOwnerId(2)
                .buildPublication(publicationDao.getById(2))
                .buildState(Subscription.StateEnum.UNPAID)
                .buildPayment(null)
                .build();

        System.out.println(dao.setInDb(subscription));

        list = dao.getAll();

        list.forEach(System.out::println);

        System.out.println(dao.delete(subscription));

        list = dao.getAll();

        list.forEach(System.out::println);

    }
}
