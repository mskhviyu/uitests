package com.example.uitests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegionPopup {
    private final ElementsCollection topRegions = $$(".default-regions__link");
    private final SelenideElement searchField = $(By.xpath("//input[@id='select-region-field']"));
    private final SelenideElement searchingResults = $(".list-results__item");
    private final SelenideElement nothingFound = $(By.xpath("//div[@class='select-regions-no-results__title']"));

    public void getOneOfTopRegion(String region) {
        topRegions.findBy(Condition.text(region)).click();
    }

    public void searchRegion (String region) {
        searchField.sendKeys(region);
    }

    public void selectFirstSearchedResult() {
        searchingResults.shouldBe(Condition.visible).click();
    }

    public void isNothingFound() {
        nothingFound.shouldBe(Condition.visible);
    }


}
