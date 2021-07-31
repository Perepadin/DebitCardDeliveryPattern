package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DebitCardDeliveryTestPattern {

    @BeforeEach
    public void openChrome() {
        open("http://localhost:9999");
    }

    @Test
    void shouldShortTestPassed() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue(DataGenerator.Registration.generateByCard("ru").getCity());
        $("[data-test-id=date]").$("[class='input__control']").click();
        $("[data-test-id=date]").$("[class='input__control']").sendKeys(Keys.chord(Keys.CONTROL + "A", Keys.DELETE));
        form.$("[data-test-id=date] input").setValue(DataGenerator.generateDate());
        form.$("[data-test-id=name] input").setValue(DataGenerator.Registration.generateByCard("ru").getName());
        form.$("[data-test-id=phone] input").setValue(DataGenerator.Registration.generateByCard("ru").getPhone());
        form.$("[data-test-id=agreement]").click();
        form.$$("[role=button]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate()));
    }

    @Test
    void shouldLongTestPassed() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue(DataGenerator.Registration.generateByCard("ru").getCity());
        $("[data-test-id=date]").$("[class='input__control']").click();
        $("[data-test-id=date]").$("[class='input__control']").sendKeys(Keys.chord(Keys.CONTROL + "A", Keys.DELETE));
        form.$("[data-test-id=date] input").setValue(DataGenerator.generateDate());
        form.$("[data-test-id=name] input").setValue(DataGenerator.Registration.generateByCard("ru").getName());
        form.$("[data-test-id=phone] input").setValue(DataGenerator.Registration.generateByCard("ru").getPhone());
        form.$("[data-test-id=agreement]").click();
        form.$$("[role=button]").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate()));
        form.$$("[role=button]").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("У вас уже запланирована встреча на другую дату. Перепланировать?\n" +
                "\n" +
                "Перепланировать"));
        $("[data-test-id=replan-notification] .button__text").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Перепланировать")).click();
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(exactText("Встреча успешно запланирована на " + $("[data-test-id=date] input").getValue()));
    }

}


