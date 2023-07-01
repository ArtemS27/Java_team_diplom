package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test //Добавление денег на пустой баланс
    public void shouldAddToEmptyBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test //Добавление денег на счет с положительным балансом (Баг)
    public void shouldAddToPositiveBalance(){
        CreditAccount account = new CreditAccount(
                1_000,
                3_000,
                15
        );

        account.add(5_000);

        Assertions.assertEquals(6_000, account.getBalance());
    }

    @Test //Добавление денег на счет с отрицательным балансом (Баг)
    public void shouldAddToNegativeBalance(){
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15);
        account.pay(1_000);
        account.add(1500);

        Assertions.assertEquals(500, account.getBalance());
    }

    @Test //Оплата при нулевом балансе не выходя за лимит
    public void shouldChargeMoneyWhenPayInLimitZeroBalance(){
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.pay(500);

        Assertions.assertEquals(-500, account.getBalance());
    }

    @Test //Оплата при положителном балансе не выходя за лимит (Баг)
    public void shouldChargeMoneyWhenPayInLimitPositiveBalance(){
        CreditAccount account = new CreditAccount(
                1_500,
                5_000,
                15
        );

        account.pay(500);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test //Оплата при отрицательном балансе не выходя за лимит (Баг)
    public void shouldChargeMoneyWhenPayInLimitNegativeBalance(){
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        account.pay(2_000);
        account.pay(500);

        Assertions.assertEquals(-2_500, account.getBalance());
    }

    @Test //Оплата при нулевом балансе сверх лимита (Баг)
    public void shouldNotChargeMoneyWhenPayOverLimitZeroBalance(){
        CreditAccount account = new CreditAccount(
                0,
                1_000,
                15
        );

        account.pay(1_500);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test //Оплата при положительном балансе сверх лимита (Баг)
    public void shouldNotChargeMoneyWhenPayOverLimitPositiveBalance(){
        CreditAccount account = new CreditAccount(
                1_500,
                1_000,
                15
        );

        account.pay(3_000);

        Assertions.assertEquals(1_500, account.getBalance());
    }

    @Test //Оплата при отрицательном балансе сверх лимита (Баг)
    public void shouldNotChargeMoneyWhenPayOverLimitNegativeBalance(){
        CreditAccount account = new CreditAccount(
                0,
                1_000,
                15
        );
        account.pay(500);
        account.pay(1_000);

        Assertions.assertEquals(-500, account.getBalance());
    }

    @Test //Списание годового процента при положительном балансе (Баг)
    public void shouldNotMadeYearChargeIfBalancePositive(){
        CreditAccount account = new CreditAccount(
                1_000,
                3_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test //Списание годоваго процента при отрицательном балансе
    public void shouldMadeYearChargeIfBalanceNegative(){
        CreditAccount account = new CreditAccount(
                0,
                1_000,
                15
        );
        account.pay(500);

        Assertions.assertEquals(-75, account.yearChange());
    }

    @Test //Вывод ошибки при отрицательном годовом проценте
    public void shouldThrowExceptionWhenRateIsNegative(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new CreditAccount(
                0,
                5_000,
                -15);
        });
    }

    @Test //Вывод ошибки при отрицательном кредитном лимите (Баг)
    public void shouldThrowExceptionWhenCreditLimitISNegative(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new CreditAccount(
                0,
                -5_000,
                15);
        });
    }

    @Test
    public void shouldShowBalance(){
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                15
        );

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldShowCreditLimit(){
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                15
        );

        Assertions.assertEquals(1_000, account.getCreditLimit());
    }

    @Test
    public void shouldShowRate(){
        CreditAccount account = new CreditAccount(
                1_000,
                1_000,
                15
        );

        Assertions.assertEquals(15, account.getRate());
    }

    @Test //Вывод ошибки при отрицательном кредитном лимите (Баг)
    public void shouldThrowExceptionWhenInitialBalanceISNegative(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {new CreditAccount(
                -1_000,
                5_000,
                15);
        });
    }
}
