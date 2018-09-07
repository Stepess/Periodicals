import model.exception.NotEnoughMoney;
import model.dao.*;
import model.entity.DTO.PublicationDto;
import model.entity.DTO.SubscriptionDto;
import model.entity.Publication;
import model.entity.Subscription;
import model.entity.User;
import model.service.builders.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class JdbcSubscriptionDaoTest {
    private static DaoFactory daoFactory;
    private final static int TEST_ID = 1000000;
    private static User user;
    private static Publication publication;
    private static Subscription subscription;

    @BeforeClass
    public static void init() {
        daoFactory = DaoFactory.getInstance();
    }

    @Before
    public void insertTestData() {

        try(UserDao dao = daoFactory.createUserDao()) {
            dao.setInDb(new UserBuilder()
                    .buildLogin("test")
                    .buildPassword("testik")
                    .buildEmail("test@test.com")
                    .buildFirstName("Test")
                    .buildLastName("Test")
                    .buildRole(User.RoleEnum.USER)
                    .buildAddress("Test, test, test, 13")
                    .buildAccount(new BigDecimal(15.5d))
                    .build());
            user = dao.getByLogin("test");
        }

        PublicationDto publicationDto = new PublicationDtoBuilder()
                .buildTitleEn("test")
                .buildTitleUa("тест")
                .buildGenreEn("test")
                .buildGenreUa("тест")
                .buildAuthor("TestInc")
                .buildPrice(new BigDecimal(10))
                .buildDescriptionEn("TestTestTest")
                .buildDescriptionUa("ТестТестТест")
                .build();

        try(PublicationDao dao = daoFactory.createPublicationDao()) {
            dao.setInDb(publicationDto);
            List<PublicationDto> result = dao.getAll().stream().filter(dto -> dto.getTitleEn().equals("test")).collect(Collectors.toList());
            publication = result.get(0).convertToInternationalizedEntity(new Locale("en"));
        }

        subscription = new SubscriptionBuilder()
                .buildStartDate(LocalDate.now())
                .buildEndDate(LocalDate.now().plusMonths(1))
                .buildOwnerId(user.getId())
                .buildState(Subscription.StateEnum.UNPAID)
                .buildPayment( new PaymentBuilder()
                        .buildBill(new BigDecimal(54.7))
                        .build())
                .buildPublication(publication)
                .build();

        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()) {
            dao.setInDb(subscription);
            List<SubscriptionDto> result = dao.getByUserLogin(user.getLogin(), Subscription.StateEnum.UNPAID.toString());
            subscription = result.get(0).convertToInternationalizedEntity(new Locale("en"));
        }
    }

    @After
    public void deleteTestData() {
        try(UserDao dao = daoFactory.createUserDao()) {
            dao.delete(user.getId());
        }
        try(PublicationDao dao = daoFactory.createPublicationDao()) {
            dao.delete(publication.getId());
        }
    }


    @Test(expected = NotEnoughMoney.class)
    public void When_UserHaveNoEnoughMoney_Then_TransactionFail() {
        /*double bill = 15.5d;
        double account = 5.1d;

        Subscription subscription = new SubscriptionBuilder(TEST_ID)
                .buildPayment(new PaymentBuilder()
                .buildBill(new BigDecimal(bill))
                .build())
                .build();
        User user = new UserBuilder(TEST_ID)
                .buildAccount(new BigDecimal(account))
                .build();*/



        try(SubscriptionDao dao = daoFactory.createSubscriptionDao()) {
            dao.pay(user, subscription);
        }
    }


}
