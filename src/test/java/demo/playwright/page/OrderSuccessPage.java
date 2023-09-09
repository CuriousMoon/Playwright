package demo.playwright.page;

import com.microsoft.playwright.Locator;
import demo.playwright.base.Generic;

public class OrderSuccessPage {

    Generic generic = new Generic("");
    private String orderSuccessMessageHeader_txt = ".complete-header";
    private String orderSuccessMessageInfo_txt = ".complete-text";
    private String successImg_altTxt = "Pony Express";

    public Locator loc_OrderSuccessMessageHeader() {
        return generic.getByLocator(orderSuccessMessageHeader_txt);
    }

    public Locator loc_OrderSuccessMessageInfo() {
        return generic.getByLocator(orderSuccessMessageInfo_txt);
    }

    public Locator loc_SuccessImg() {
        return generic.getByAltTxt(successImg_altTxt);
    }



}
