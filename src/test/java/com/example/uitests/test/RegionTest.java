package com.example.uitests.test;

import com.example.uitests.common.BaseTest;
import com.example.uitests.steps.MainPageSteps;
import org.testng.annotations.Test;

public class RegionTest extends BaseTest {

    MainPageSteps mainPageSteps = new MainPageSteps();

    @Test
    void changeRegionInSmallPopup(){
        String city = "Санкт-Петербург";

        mainPageSteps
                .rejectPreselectedRegion()
                .chooseOneOfTopRegion(city)
                .checkCurrentRegion(city);
    }

    @Test
    void changeRegionPopupAutoSelect(){
        String city = "Орел";

        mainPageSteps
                .confirmPreselectedRegion()
                .openRegionPopup()
                .searchRegion(city)
                .selectFirstRegionFromSearch()
                .checkCurrentRegion(city);
    }

    @Test
    void regionNotFound(){
        mainPageSteps
                .confirmPreselectedRegion()
                .openRegionPopup()
                .searchRegion("regionNotFound")
                .checkIsNothingFound();
    }
}
