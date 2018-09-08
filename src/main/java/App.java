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
import model.service.resource.manager.LocalePatternManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

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


        System.out.println(dao.addPublication(user));
        System.out.println(dao.getAll());

        user.setNationalField("address", "Дуже далека-далека галактика");

        System.out.println(dao.editPublication(user));
        System.out.println(dao.getAll());
        System.out.println(dao.deletePublication(user));
        System.out.println(dao.getAll());*/

       /* PublicationDao dao = factory.createPublicationDao();

        System.out.println(dao.getAll());



        Publication publication = new PublicationBuilder(5,"title", "genre")
                .buildDescription("description")
                .buildAuthor("authror")
                .buildPrice(BigDecimal.valueOf(6554.25))
                .build();

        System.out.println(dao.addPublication(publication));

        System.out.println(dao.getAll());
*/

/*
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

        System.out.println(dao.addPublication(subscription));

        list = dao.getAll();

        list.forEach(System.out::println);

        System.out.println(dao.deletePublication(subscription));

        list = dao.getAll();

        list.forEach(System.out::println);*/


        /*String query = "?param1=4&param2=5&";
        String query2=  "param1=4&";
        String paramName = "param2";
        String paramValue = "0";


        String str1 = paramName+"=.*&";
        String str2 = paramName+"="+paramValue+"&";

        query=query.replaceAll(str1, str2);
        query=query.substring(0,query.length()-1);
        System.out.println(query);*/

        /*LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println(time.format(formatter));*/
       /* LocalDateTime localDateTime = LocalDateTime.now();
        Locale locale = new Locale("en", "US");
        LocalePatternManager manager = new LocalePatternManager(locale);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(manager.getProperty("pattern.time"), locale);
        System.out.println(manager.getProperty("pattern.date"));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(manager.getProperty("pattern.date"), locale);
        System.out.println((localDateTime.toLocalDate().format(dateFormatter) + " " +
                localDateTime.toLocalTime().format(timeFormatter)));*/
/*
       Toyota t = (Toyota) new Car();
        System.out.println(t);*/

        //Integer i = new Double(12.5d);
        /*Double d = new Double(12.5);
        int i = (int) 5+d + 5);
        System.out.println(i);*/

        /*Object o = new Toyota();
        Car car = (Car) o;
        System.out.println(car);

        String s;
        s.substring(1);
        */

      /*
       Byte b=0;
        b += 3;*/


    }
}

class Car{
}

class Toyota extends Car{}
