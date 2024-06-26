package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderingCardTest {

    @Test
    void shouldValidTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий Теркин");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    //Имя на латинском
    @Test
    void shouldLatNameTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Vasily Terkin");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    //Без имени
    @Test
    void shouldNoNameTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    //5 цифр номера
    @Test
    void shouldFiveNambersTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий Теркин");
        form.$("[data-test-id=phone] input").setValue("+79270");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //номер телефона без +
    @Test
    void shouldNoPlusTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий Теркин");
        form.$("[data-test-id=phone] input").setValue("79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //номер телефона латинскими буквами
    @Test
    void shouldNomberLatTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий Теркин");
        form.$("[data-test-id=phone] input").setValue("AAAAAAAAAAAA");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //номер телефона заявленая длинна +1
    @Test
    void shouldNomberPlusOneTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий Теркин");
        form.$("[data-test-id=phone] input").setValue("+792700000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    //номер телефона заявленая длинна -1
    @Test
    void shouldNomberMinusOneTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий Теркин");
        form.$("[data-test-id=phone] input").setValue("+7927000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button__text").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }


    //не нажимаем согласие
    @Test
    void shouldNoAgreementTest() {
        open("http://localhost:9999");

        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий Теркин");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$(".button__text").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldBe(visible);
    }

}


