import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {
    @Test
    void shouldSendForm() {
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys. DELETE);
        $("[data-test-id=date] input").val(date);
        $("[data-test-id=name] input").val("Ольга-Мария Петрова");
        $("[data-test-id=phone] input").val("+79104657452");
        $(By.className("checkbox__text")).click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]")
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
