package com.example.uitests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement chooseAnotherRegionBtn = $("button[class*=confirm-region__btn--rejection]");
    private final SelenideElement confirmRegionBtn = $("button[class*=confirm-region__btn]");
    private final SelenideElement regionBtn = $(".current-region__text");
    private final SelenideElement modals = $(".modals-container");

    public void chooseAnotherRegion() {
        chooseAnotherRegionBtn.click();
    }

    public void confirmRegion() {
        confirmRegionBtn.click();
    }

    public void pressRegionButton() {
        regionBtn.click();
    }

    public String getCurrentRegionName() {
        modals.shouldBe(Condition.disappear);
        return regionBtn.getText();
    }
}
