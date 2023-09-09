package demo.playwright.page;

import com.microsoft.playwright.Locator;
import demo.playwright.base.Generic;

import java.util.ArrayList;
import java.util.List;

public class CartPage {

    Generic generic = new Generic("");
    private String cartItems_WE = "//div[@class='cart_item']";
    private String itemName_WE = "//div[@class='cart_item']//div[@class='inventory_item_name']";
    private String itemPrice_txt = "//div[text()='%s']/parent::a/following-sibling::div//div[@class='inventory_item_price']";
    private String checkout_btn = "checkout";


    public Locator loc_allCartItemsWE(){
        return generic.getByLocator(cartItems_WE);
    }

    public Locator loc_itemNamesInCart(){
        return generic.getByLocator(itemName_WE);
    }

    public Locator loc_itemPrice(String name){
        return generic.getByLocator(String.format(itemPrice_txt,name));
    }

    public Locator loc_checkoutBtn(){
        return generic.getById(checkout_btn);
    }

    /**
     * This method return the total different items present in cart
     * @return int
     */
    public int getTotalCartItemCount(){
        return loc_allCartItemsWE().count();
    }

    /**
     * This return the list of names of items present in cart
     * @return List<String>
     */
    public List<String> getNameOfAllItemsInCart(){
        List<String> itemNames = new ArrayList<>();
        int totalCartItemCount = getTotalCartItemCount();
        while(totalCartItemCount>0){
            --totalCartItemCount;
            itemNames.add(generic.getInnerText(loc_itemNamesInCart().nth(totalCartItemCount)));
        }
        return itemNames;
    }

    /**
     * This method returns the price of the element
     * @param itemName
     * @return String
     */
    public String getItemPrice(String itemName){
        return generic.getInnerText(loc_itemPrice(itemName));
    }

    /**
     * This method clicks on checkout button present on cart page
     */
    public void clickOnCheckout(){
        generic.click(loc_checkoutBtn());
    }
}
