package demo.playwright.base;

import com.microsoft.playwright.*;

public final class Base {

    private static String configFile = "./config.properties";
    private static  ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static  ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static  ThreadLocal<Page> page = new ThreadLocal<>();
    private static ThreadLocal<Base> base = new ThreadLocal<>();

    private static void setPlaywright(){
        playwright.set(Playwright.create());
    }

    private static void setBrowser(){
        String binary = ReadPropertyFile.getProperty("binary",configFile);
        String channel = ReadPropertyFile.getProperty("channel",configFile);
        String headless = ReadPropertyFile.getProperty("headless",configFile);
        switch (binary){
            case "chromium":
                browser.set( getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.valueOf(headless))));
                break;
            case "webkit":
                browser.set( getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.valueOf(headless))));
                break;
            case "firefox":
                browser.set( getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.valueOf(headless))));
                break;
            default:
                browser.set( getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.valueOf(headless))));
                break;
        }
    }

    private static void setBrowserContext(){
        browserContext.set(getBrowser().newContext());
    }

    protected static void setPage(){
        page.set(getBrowserContext().newPage());
    }

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContext.get();
    }

    public static Page getPage() {
        return page.get();
    }

    public static Page getBasePage(){
        if(base.get()==null)
            base.set(new Base());
        return base.get().getPage();}

    private Base(){
        setPlaywright();
        setBrowser();
        setBrowserContext();
        setPage();
    }











}
