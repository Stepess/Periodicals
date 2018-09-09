package model.service;

import model.entity.Subscription;
import model.entity.User;
import model.exception.NotEnoughMoneyException;
import model.service.builders.PaymentBuilder;
import model.service.builders.SubscriptionBuilder;
import model.service.builders.UserBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SubscriptionServiceTest {
    private static SubscriptionService service;

    @BeforeClass
    public static void init() {
        service = new SubscriptionService();
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void GivenUserHaveNotEnoughMoneyWhenUserPaySubscriptionThenThrowException() {
        User user = new UserBuilder()
                .buildLogin("test")
                .buildPassword("testik")
                .buildEmail("test@test.com")
                .buildFirstName("Test")
                .buildLastName("Test")
                .buildRole(User.RoleEnum.USER)
                .buildAddress("Test, test, test, 13")
                .buildAccount(new BigDecimal(15.5d))
                .build();

        Subscription subscription = new SubscriptionBuilder()
                .buildStartDate(LocalDate.now())
                .buildEndDate(LocalDate.now().plusMonths(1))
                .buildOwnerId(user.getId())
                .buildState(Subscription.StateEnum.UNPAID)
                .buildPayment( new PaymentBuilder()
                        .buildBill(new BigDecimal(54.7))
                        .build())
                .build();

        service.paySubscription(user, subscription);
    }
}