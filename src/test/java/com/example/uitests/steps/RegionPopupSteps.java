package com.example.uitests.steps;

import com.example.uitests.pages.RegionPopup;
import io.qameta.allure.Step;

public class RegionPopupSteps {
    RegionPopup regionPopup = new RegionPopup();

    @Step("Выбрать один из основных регионов [{region}]")
    public MainPageSteps chooseOneOfTopRegion(String region) {
        regionPopup.getOneOfTopRegion(region);
        return new MainPageSteps();
    }

    @Step("Найти регион [{region}]")
    public RegionPopupSteps searchRegion(String region) {
        regionPopup.searchRegion(region);
        return this;
    }

    @Step("Выбрать первый регион из вариантов поиска")
    public MainPageSteps selectFirstRegionFromSearch() {
        regionPopup.selectFirstSearchedResult();
        return new MainPageSteps();
    }

    @Step("Проверить, что поиск ничего не нашел")
    public void checkIsNothingFound() {
        regionPopup.isNothingFound();
    }

}
