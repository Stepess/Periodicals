import model.dao.DaoFactory;
import model.dao.PublicationDao;
import model.dao.UserDao;
import model.dao.jdbc.JdbcDaoFactory;
import model.entity.Publication;
import model.entity.User;
import model.service.builders.PublicationBuilder;
import model.service.builders.UserBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        DaoFactory factory = new JdbcDaoFactory();
        /*UserDao dao = factory.createUserDao();
        System.out.println(dao.getAll());

        User user = new UserBuilder(7)
                 .buildLogin("Jedi")
                .buildPassword("asdasdsad")
                .buildEmail("email@email.com")
                .buildRole(User.RoleEnum.USER)
                .buildFirstName("Obi-Wan")
                .buildLastName("Kenobi")
                .buildAddress("Far-far galaxy")
                .buildAccount(BigDecimal.valueOf(54664.21))
                .build();
        Map<String, String> map = new HashMap<>();
        map.put("firstName", "Обі-Ван");
        map.put("lastName", "Кенобі");
        map.put("address", "Далека-далека галактика");
        user.setNationalFields(map);
        user.setPassword("may0the0force");



        System.out.println(dao.setInDb(user));
        System.out.println(dao.getAll());

        map.put("address", "Дуже далека-далека галактика");
        user.setNationalFields(map);

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
    }
}
