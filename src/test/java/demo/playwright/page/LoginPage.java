package demo.playwright.page;

import com.microsoft.playwright.Locator;
import demo.playwright.base.Generic;

public class LoginPage {
    private Generic generic = new Generic("");
    private String login_btn = "login-button";
    private String username = "user-name";
    private String password = "password";

    public Locator loc_LoginBtn(){
        return generic.getById(login_btn);
    }

    public Locator loc_Username(){
        return generic.getById(username);
    }

    public Locator loc_Password(){
        return generic.getById(password);
    }

    /**
     * This method is used for login with given credentials
     * @param username
     * @param password
     */
    public void login(String username, String password){
        generic.fill(loc_Username(),username);
        generic.fill(loc_Password(),password);
        generic.click(loc_LoginBtn());
    }
}
