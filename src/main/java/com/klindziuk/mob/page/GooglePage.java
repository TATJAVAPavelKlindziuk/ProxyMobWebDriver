package com.klindziuk.mob.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {
	private static final String TITLE = "Google";
	WebDriver driver;
		 
    @FindBy(name = "q")
    private WebElement searchBox;
 
    @FindBy(name = "btnG")
    private WebElement searchbutton;
     
    @FindAll(@FindBy(how = How.XPATH, using = "//*[@role='listbox']") )
    private List <WebElement> results;
    
	public GooglePage(WebDriver driver){
		this.driver = driver;
	}
 
    public void searchFor(String searchQuery) {
        searchBox.sendKeys(searchQuery);
        searchBox.submit();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase()
						.startsWith(TITLE.toLowerCase());
			}
		});
    }
    
    public String getSearchQueryText(){
    	String result = searchBox.getAttribute("value");
    	return result;
    }
    
    public List <WebElement> getResults() {
    	return results;
    }
    
    public String getTitle(){
    	return driver.getTitle();
    }
}
