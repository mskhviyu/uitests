package com.example.uitests.test;

import com.codeborne.selenide.Condition;
import com.example.uitests.common.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegionTest extends BaseTest {
    @Test
    void changeRegionInSmallPopup(){
        String city = "Санкт-Петербург";

        open("/");
        $(By.cssSelector("button[class*=confirm-region__btn--rejection]")).click();
        $(By.xpath("//button[@class='default-regions__link' and contains(text(), 'Санкт-Петербург')]")).click();
        String selectedCity = $(By.xpath("//span[@class='current-region__text' and contains(text(), 'Санкт-Петербург')]")).getText();
        Assert.assertEquals(selectedCity, city);
    }

    @Test
    void changeRegionPopupAutoSelect(){
        String city = "Орел";

        open("/");
        $(By.cssSelector("button[class*=confirm-region__btn]")).click();
        $(By.xpath("//button[@id='selectRegion']")).click();
        $(By.xpath("//input[@id='select-region-field']")).sendKeys("орел");
        $(By.xpath("//button[starts-with(@class, 'list-results__link')]")).click();
        String selectedCity = $(By.xpath("//span[@class='current-region__text' and contains(text(), 'Орел')]")).getText();
        Assert.assertEquals(selectedCity, city);
    }

    @Test
    void regionNotFound(){
        open("/");
        $(By.cssSelector("button[class*=confirm-region__btn]")).click();
        $(By.xpath("//button[@id='selectRegion']")).click();
        $(By.xpath("//input[@id='select-region-field']")).sendKeys("regionNotFound");
        $(By.xpath("//div[@class='select-regions-no-results__title']")).shouldBe(Condition.visible);
    }
}
