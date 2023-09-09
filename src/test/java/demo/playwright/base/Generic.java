package demo.playwright.base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class Generic {

    private Base base;
    protected static ThreadLocal<Page> page = new ThreadLocal<>();

    /**
     * Constructor for initilizing page
     */
    public Generic(){
        page.set(base.getBasePage());
    }

    /**
     * Constructor for class
     * @param set
     */
    public Generic(String set){}

    /**
     * This method return the current Page object
     * @return
     */
    public static Page getPage(){
        return page.get();
    }

    /**
     * This method is returns Locator, used to access locator by id
     * @param selector
     * @return Locator
     */
    public Locator getById(String selector){
        return page.get().locator("#"+selector);
    }

    /**
     * This method returns locator, used to get locator by xpath or css
     * @param selector
     * @return Locator
     */
    public Locator getByLocator(String selector){
        return page.get().locator(selector);
    }

    /**
     * This method return locator, used to get locator by inner text with exact match
     * @param selector
     * @return Locator
     */
    public Locator getByText(String selector){
        return page.get().getByText(selector,new Page.GetByTextOptions().setExact(true));
    }

    /**
     * This method returns Locator, used to get locator by title attribute
     * @param selector
     * @return Locator
     */
    public Locator getByTitle(String selector){
        return page.get().getByTitle(selector,new Page.GetByTitleOptions().setExact(true));
    }

    /**
     * This method returns Locator, used to get locator by label tag
     * @param selector
     * @return Locator
     */
    public Locator getByLabel(String selector){
        return page.get().getByLabel(selector,new Page.GetByLabelOptions().setExact(true));
    }

    /**
     * This method returns locator, used to get locator by placeholder value
     * @param selector
     * @return Locator
     */
    public Locator getByPlaceholder(String selector){
        return page.get().getByPlaceholder(selector,new Page.GetByPlaceholderOptions().setExact(true));
    }

    /**
     * This method return Locator, used to access locator by alt text of image
     * @param selector
     * @return Locator
     */
    public Locator getByAltTxt(String selector){
        return page.get().getByAltText(selector,new Page.GetByAltTextOptions().setExact(true));
    }

    /**
     * This method return Locator, used to get locator using test-data-id
     * @param id
     * @return Locator
     */
    public Locator getByTestDataId(String id){
        return page.get().getByTestId(id);
    }

    /**
     * This method waits for given amount of time in milliseconds
     * @param millis
     */
    public void hardWait(int millis){
        page.get().waitForTimeout(millis);
    }

    /**
     * This method is used to navigate to given url
     * @param url
     */
    public void navigate(String url) {
        page.get().navigate(url);
    }

    /**
     * This methods fills given text at locator and then click enter
     * @param locator
     * @param text
     */
    public void fillAndEnter(Locator locator,String text){
        locator.fill(text);
        locator.press("Enter");
    }

    /**
     * This method is used to fill text at given locator
     * @param locator
     * @param text
     */
    public void fill(Locator locator,String text){
        locator.fill(text);
    }

    /**
     * This method is used to click at given locator
     * @param locator
     */
    public void click(Locator locator){
        locator.click();
    }

    /**
     * This method return the inner text of given locator
     * @param locator
     * @return String
     */
    public String getInnerText(Locator locator){
        return locator.innerText();
    }

}
