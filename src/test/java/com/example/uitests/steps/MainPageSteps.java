package com.example.uitests.steps;

import com.example.uitests.pages.MainPage;
import io.qameta.allure.Step;
import org.testng.Assert;

public class MainPageSteps {
    MainPage mainPage = new MainPage();

    @Step("Открыть попап региона")
    public RegionPopupSteps openRegionPopup(){
        mainPage.pressRegionButton();
        return new RegionPopupSteps();
    }

    @Step("Подтвердить предвыбранный регион")
    public MainPageSteps confirmPreselectedRegion() {
        mainPage.confirmRegion();
        return this;
    }

    @Step("Сменить предвыбранный регион")
    public RegionPopupSteps rejectPreselectedRegion() {
        mainPage.chooseAnotherRegion();
        return new RegionPopupSteps();
    }

    @Step("Проверить, что текущий регион совпадает с {region}")
    public void checkCurrentRegion(String region) {
        String currentRegion = mainPage.getCurrentRegionName();
        Assert.assertEquals(currentRegion, region);
    }
}
