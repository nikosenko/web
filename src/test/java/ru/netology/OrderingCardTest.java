package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderingCardTest {

    @Test
    void shouldValidTest

    {
        open(" http://0.0.0.0:9999");
        SelenideElement from = $(".from");
        from.$("[data-test-id=name] input").setValue("Василий Теркин");
        from.$("[data-test-id=phone] input").setValue("+79270000000");
        from.$("[data-test-id=agreement]").click();
        from.$("[button__text]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }

}
