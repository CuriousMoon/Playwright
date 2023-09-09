package demo.playwright.page;

import com.microsoft.playwright.Locator;
import demo.playwright.base.Generic;

public class AllProductsPage {
    private Generic generic = new Generic("");
    private String addToCart_btn = "//div[text()='%s']//ancestor::div[@class='inventory_item_description']//button";
    private String price_lbl = "//div[text()='%s']//ancestor::div[@class='inventory_item_description']//div[@class='inventory_item_price']";
    private String shoppingCart_btn = "shopping_cart_container";
    private String shoppingCartCount_txt = "#shopping_cart_container>a>span";

    public Locator loc_AddToCart(String productName){
        return generic.getByLocator(String.format(addToCart_btn,productName));
    }

    public Locator loc_ProductPrice(String productName){
        return generic.getByLocator(String.format(price_lbl,productName));
    }

    public Locator loc_ShoppingCart(){
        return generic.getById(shoppingCart_btn);
    }

    public Locator loc_ShoppingCartItemCount(){
        return generic.getByLocator(shoppingCartCount_txt);
    }

    /**
     * This method is used to add given product in cart
     * @param productName
     */
    public void addProduct(String productName){
        generic.click(loc_AddToCart(productName));
    }

    /**
     * This method clicks on cart button
     */
    public void clickOnCartButton(){
        generic.click(loc_ShoppingCart());
    }

    /**
     * This methods returns the product quantity displayed over cart button
     * @return String
     */
    public String getCartProductQuantity(){
        return generic.getInnerText(loc_ShoppingCartItemCount());
    }

    /**
     * This method returns the price of given product
     * @param productName
     * @return String
     */
    public String getProductPrice(String productName){
        return generic.getInnerText(loc_ProductPrice(productName));
    }

}
