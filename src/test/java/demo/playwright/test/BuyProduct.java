package demo.playwright.test;

import demo.playwright.base.Generic;
import demo.playwright.base.ReadPropertyFile;
import demo.playwright.page.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Arrays;


public class BuyProduct {
    Generic generic;
    LoginPage loginPage;
    AllProductsPage allProductsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderSuccessPage orderSuccessPage;
    String loginURL, loginPassword, loginUsername;
    String config = "./config.properties";
    SoftAssert softAssert;

    @BeforeClass
    public void beforeClass(){
        loginURL = ReadPropertyFile.getProperty("loginURL",config);
        loginPassword = ReadPropertyFile.getProperty("password",config);
        loginUsername = ReadPropertyFile.getProperty("username",config);
        softAssert = new SoftAssert();
        loginPage = new LoginPage();
        allProductsPage = new AllProductsPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        orderSuccessPage= new OrderSuccessPage();
    }

    @BeforeMethod
    public void beforeMethod(){
        generic = new Generic();
    }

    @Test
    public void test_Successful_Checkout(){

        // Navigate to Sauce Labs home page
        generic.navigate(loginURL);

        //Login on home page
        loginPage.login(loginUsername,loginPassword);

        //Select Sauce Labs Backpack from products
        allProductsPage.addProduct("Sauce Labs Backpack");

        //Assert that product is added
        softAssert.assertEquals(generic.getInnerText(allProductsPage.loc_AddToCart("Sauce Labs Backpack")),
                "Remove","[Failed]: Sauce Labs Backpack not added to cart");

        //Select Sauce Labs Backpack from products
        allProductsPage.addProduct("Sauce Labs Bike Light");

        //Assert that product is added
        softAssert.assertEquals(generic.getInnerText(allProductsPage.loc_AddToCart("Sauce Labs Bike Light")),
                "Remove","[Failed]: Sauce Labs Bike Light not added to cart");

        //Get Sauce Labs Backpack price
        String backpackPrice = allProductsPage.getProductPrice("Sauce Labs Backpack");

        //Get Sauce labs bike light price
        String bikeLightPrice = allProductsPage.getProductPrice("Sauce Labs Bike Light");

        //Calculate total product cost
        float totalPrice = Float.parseFloat(backpackPrice.substring(1))
                +Float.parseFloat(bikeLightPrice.substring(1));

        //Over cart button verify the added item count
        softAssert.assertEquals(allProductsPage.getCartProductQuantity(),"2", "[Failed]: Product quantity not correctly updated in cart");

        //Click on cart button
        allProductsPage.clickOnCartButton();

        //Assert that total count of different items is 2
        softAssert.assertEquals(cartPage.getTotalCartItemCount(),2, "[Failed]: Total item count is different from expected count in cart");

        //Assert that all added items are present in list
        softAssert.assertEquals(cartPage.getNameOfAllItemsInCart().containsAll(Arrays.asList(new String[]{"Sauce Labs Backpack","Sauce Labs Bike Light"})),true,"[Failed]: Items in cart does not match");

        //Assert that the price of items in cart is same as on product home page
        softAssert.assertEquals(cartPage.getItemPrice("Sauce Labs Backpack"),backpackPrice, "[Failed]: Sauce Labs Backpack price does not match in cart");
        softAssert.assertEquals(cartPage.getItemPrice("Sauce Labs Bike Light"),bikeLightPrice, "[Failed]: Sauce Labs Bike Light price does not match in cart");

        //Click on checkout button
        cartPage.clickOnCheckout();

        //Assert Checkout Information page title
        softAssert.assertEquals(checkoutPage.getCheckoutPageTitle(),"Checkout: Your Information","[Failed]: Title is not same as expected");

        //Fill checkout information and click on continue
        checkoutPage.fillCheckoutInfo("Mansi","Gupta","201303");
        checkoutPage.clickOnContinue();

        //Assert checkout overview title
        softAssert.assertEquals(checkoutPage.getCheckoutPageTitle(),"Checkout: Overview","[Failed]: Checkout overview page title is not as expected");

        //Assert item and its price on checkout page
        softAssert.assertEquals(cartPage.getNameOfAllItemsInCart().containsAll(Arrays.asList(new String[]{"Sauce Labs Backpack","Sauce Labs Bike Light"})),true,"[Failed]: Items on checkout page does not match");
        softAssert.assertEquals(cartPage.getItemPrice("Sauce Labs Backpack"),backpackPrice, "[Failed]: Sauce Labs Backpack price does not match in cart");
        softAssert.assertEquals(cartPage.getItemPrice("Sauce Labs Bike Light"),bikeLightPrice, "[Failed]: Sauce Labs Bike Light price does not match in cart");

        //Assert that total calculated product value is same as sub-total product value
        softAssert.assertEquals(Float.parseFloat(checkoutPage.getPrice("subTotal")),totalPrice,"[Failed]: Calculates and displayed sub total value mismatched");

        //Asset total value is sum of tax and subtotal value
        Float tax = Float.parseFloat(checkoutPage.getPrice("tax"));
        Float subTotal = Float.parseFloat(checkoutPage.getPrice("subTotal"));
        Float calcultedTotal = tax+subTotal;
        Float actualTotal = Float.parseFloat(checkoutPage.getPrice("total"));
        softAssert.assertEquals(actualTotal,calcultedTotal,"[Failed]: Calculated and displayed total mismatched");

        //Click on finish button
        checkoutPage.clickOnFinish();

        //Assert Successful order placed
        softAssert.assertEquals(generic.getInnerText(orderSuccessPage.loc_OrderSuccessMessageHeader()),"Thank you for your order!","[Failed]: Success message header not present");
        softAssert.assertEquals(generic.getInnerText(orderSuccessPage.loc_OrderSuccessMessageInfo()),"Your order has been dispatched, and will arrive just as fast as the pony can get there!","[Failed]: Success message body not present");
        softAssert.assertEquals(orderSuccessPage.loc_SuccessImg().isVisible(),true,"[Failed]: Success image not visible");

        softAssert.assertAll();
    }
}
