package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void dontShouldLessMinimumBalance() { // начальный баланс меньше минимального баланса - составлен баг репорт
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 5_000, 10_000, 5);
        });
    }

    @Test
    public void dontShouldExceedMaxBalance() { // начальный баланс больше максимального баланса - составлен баг репорт
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(100_000, 1_000, 10_000, 5);
        });
    }

    @Test
    public void minBalanceDontShouldGreaterMaxBalance() { // минимальный баланс больше максимального - составлен баг репорт
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 50_000, 10_000, 5);
        });
    }

    @Test
    public void shouldMessRateCannotBeNegative() { //процентная ставка не может быть отрицательной (вывод сообщения)
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(2_000, 1_000, 10_000, -1);
        });
    }

    @Test
    public void minBalanceDontShouldNegative() { // отрицательный минимальный баланс - составлен баг репорт
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(1_000, -1_000, 10_000, 5);
        });
    }

    @Test
    public void maxBalanceDontShouldNegative() { // отрицательный максимальный баланс - составлен баг репорт
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(1_000, 1_000, -10_000, 5);
        });
    }

    @Test
    public void initialBalanceDontShouldNegative() { // отрицательный начальный баланс - составлен баг репорт
        Assertions.assertThrows(java.lang.IllegalArgumentException.class, () -> {
            new SavingAccount(-1_000, 1_000, 10_000, 5);
        });
    }

    @Test
    public void shouldWithdrawMoneyFromBalance() { // платеж
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(1_000);
        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }

    @Test
    public void paymentShouldNotThrough() { // после покупки баланс меньше минимального баланса - составлен баг репорт
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );
        account.pay(3_000);
        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void balanceShouldEqualsMinBalance() { // баланс после оплаты равен минимальному балансу
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.pay(1_000);
        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }

    @Test
    public void shouldReplenishBalance() { // пополнение баланса - БАГ
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(1_000);
        Assertions.assertEquals(1_000 + 2_000, account.getBalance());
    }

    @Test
    public void shouldNotReplenishMoreMaxBalance() { // пополнение баланса на сумму больше максимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                5_000,
                5
        );
        account.add(5_000);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldReplenishToMaxBalance() { // пополнение баланса до суммы равной максимальному балансу - БАГ
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                5_000,
                5
        );
        account.add(3_000);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldAddLessThanMaxBalance() { // пополнение баланса на сумму меньше максимальной - составлен баг репорт
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        account.add(3_000);
        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldCalculatePercentageOfTheBalance() { // процент от баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(100, account.yearChange());
    }

    @Test
    public void shouldRoundTheBalance() { // округление процента - БАГ
        SavingAccount account = new SavingAccount(
                1_499,
                1_000,
                10_000,
                50
        );
        Assertions.assertEquals(749, account.yearChange());
    }

    @Test
    public void shouldShowMinBalance() { // показывает минимальный баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(1_000, account.getMinBalance());
    }

    @Test
    public void shouldShowMaxBalance() { // показывает максимальный баланс
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );
        Assertions.assertEquals(10_000, account.getMaxBalance());
    }
}
