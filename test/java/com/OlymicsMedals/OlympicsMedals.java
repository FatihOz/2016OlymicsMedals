package com.OlymicsMedals;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OlympicsMedals {

	WebDriver driver;
	
	Map<String, Integer> gold = new HashMap<>();
	Map<String, Integer> silver = new HashMap<>();
	Map<String, Integer> bronze = new HashMap<>();
	Map<String, Integer> total = new HashMap<>();
	
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.manage().window().fullscreen();
    }
    @Test
    public void sortTest() {
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.");
        /// READING THE COLUMN 1 AND STORING IN LIST (FROM 1-10)
        List<Integer> list1 = new ArrayList<Integer>();
        String xpath1 = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]";
        int rowNumber = driver.findElements(By.xpath(xpath1)).size();
        for (int i = 1; i < rowNumber; i++) {
            String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
                    + "]/td[1]";
            list1.add(Integer.parseInt(driver.findElement(By.xpath(xpath)).getText()));
        }
        System.out.println(list1);
        SortedSet<Integer> sort = new TreeSet<Integer>(list1);
        System.out.println(sort);
        Iterator iter = sort.iterator();
        for (Integer each : list1) {
            assertEquals(each, iter.next());
        }
        /// clicking on NOC
        driver.findElement(
                By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//th[.='NOC']"))
                .click();
        /// Now verify that the table is now sorted by the country names
        ////// READING DATA IN AND STORING THE LIST
        List<String> list2 = new ArrayList<String>();
        String xpath2 = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th";
        int rowNOC = driver.findElements(By.xpath(xpath2)).size();
        for (int i = 1; i < rowNOC; i++) {
            String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
                    + "]/th"; /// dynamic table ITERATE THE LOOP ["+i+"]
            list2.add(driver.findElement(By.xpath(xpath)).getText());
        }
        System.out.println(list2);
        SortedSet<String> sortNOC = new TreeSet<String>(list2);
        System.out.println(sortNOC);
        Iterator iterNOC = sortNOC.iterator();
        for (String each1 : list2) {
            assertEquals(each1, iterNOC.next());
        }
        
/// STEP 5  Verify that Rank column is not in ascending order anymore. Use TestNG assertions.
        
        List<Integer> list3 = new ArrayList<Integer>();
        
        String xpath3 = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[1]";
        int rowNumber1 = driver.findElements(By.xpath(xpath3)).size();
        for (int i = 1; i < rowNumber1; i++) {
            String xpath = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
                    + "]/td[1]";
            list3.add(Integer.parseInt(driver.findElement(By.xpath(xpath)).getText()));
        }
        System.out.println(list3);
        SortedSet<Integer> notAscen = new TreeSet<>(list3);
        System.out.println(notAscen);
        Iterator iter1 = sort.iterator();
        for (Integer each : list3) {
            assertNotEquals(each, iter1.next());
        }
   
    }
    @Test
    public void theMost() {
        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.");
        
        goldMedals();
        System.out.println(gold);
        System.out.println("=======================================================");
        
        silverMedals();
        System.out.println(silver);
        System.out.println("=======================================================");
        
        bronzeMedals();
        System.out.println(bronze);
        System.out.println("=======================================================");
        
        totalMedals();
        System.out.println(totalMedals());
        System.out.println("=======================================================");
        
    }
    public  void goldMedals() {
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String goldMedal = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[2]";
        int goldNumber = driver.findElements(By.xpath(goldMedal)).size();
        
        for (int i = 1; i <= goldNumber; i++) {
            String goldMedal1 = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+i+"]/td[2]";
            
            /// STORES INTO A GOLD string as a Integer 
         int temp = Integer.parseInt(driver.findElement(By.xpath(goldMedal1)).getText());   
         
         String xpathCountry = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
                    + "]/th"; /// dynamic table ITERATE THE LOOP ["+i+"]
         
         String tempCountry = driver.findElement(By.xpath(xpathCountry)).getText();  
         
         gold.put(tempCountry, temp);
         
        }
        System.out.println(gold);
	
    }
    public void silverMedals() {
    	String silverMedal = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]";
        int silverNumber = driver.findElements(By.xpath(silverMedal)).size();
        
        for(int i =1; i<=silverNumber; i++) {
        	String silverMedal1= "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr["+i+"]/td[3]";
        	
        	int temp = Integer.parseInt(driver.findElement(By.xpath(silverMedal1)).getText());
        	
        	String xpathCountry = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
                    + "]/th";
        	String tempCountry = driver.findElement(By.xpath(xpathCountry)).getText();
        	
        	silver.put(tempCountry,temp);
        }
        
    }
    public void bronzeMedals() {
    	String bronzeMedal = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]";
    	int bronzeNumber = driver.findElements(By.xpath(bronzeMedal)).size();
    	
    	for(int i = 1; i<=bronzeNumber; i++) {
    		String bronzeMedal1 =  "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
                    + "]/td[4]";
    		int temp = Integer.parseInt(driver.findElement(By.xpath(bronzeMedal1)).getText());
    		String xpathCountry = "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[" + i
                    + "]/th";
    		String tempCountry = driver.findElement(By.xpath(xpathCountry)).getText();
    		
    		bronze.put(tempCountry,temp);
    	}
    }
    public String totalMedals() {
        List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
        List<WebElement> totalMedals = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[5]"));
        
        int max = Integer.parseInt(totalMedals.get(0).getText());
        int countryNum = 0;
        for(int i = 1; i < totalMedals.size()-1; i++) {
            if (Integer.parseInt(totalMedals.get(i).getText()) > max) {
                max = Integer.parseInt(totalMedals.get(i).getText());
                countryNum = i;
            }
        }
        return lsCountries.get(countryNum).getText().trim();
    }
    @Test
    public void countryByMedals() {
    	driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.");
    	System.out.println("Silver Medals 18: " +  silver18());
    }
    public List<String> silver18() {
    	List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
    	List<WebElement> lsSilver = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
    	
    List<String> silver18 = new ArrayList<>();
    
    for(int i = 1; i<lsSilver.size(); i++ ) {
    	if(Integer.parseInt(lsSilver.get(i).getText())==18) {
    		silver18.add(lsCountries.get(i).getText());
    	}
    }
    return silver18;
    
    }
    @Test
    public void getIndex() {
    	driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.");
    	 System.out.println("Get indext : "+ rowCol("Germany"));
    }
    public String rowCol(String country) {
    	List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
    	String rowCol = "";
    	for(int i=0; i<lsCountries.size();i++) {
    		if(country.equalsIgnoreCase(lsCountries.get(i).getText().trim().substring(0, lsCountries.get(i).getText().trim().indexOf(" ")))) {
    			rowCol = "" + (i+1) + " 2";
    		}
    	}
    	return rowCol;
    }
    @Test
    public void getSum() {
    	driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.");
    	System.out.println("Get Sum Number : " + bronze18());
    }
  
    	public List<String> bronze18(){
        	List<WebElement> lsCountries = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/th"));
        	List<WebElement> lsBronze = driver.findElements(By.xpath("//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr/td[3]"));
        	
        List<String> bronze18= new ArrayList<>();
        
        for(int i = 1; i<lsBronze.size(); i++ ) {
        	int num1 = Integer.parseInt(lsBronze.get(i).getText());
        	for(int j = 1; j<lsBronze.size(); j++) {
        		int num2 = Integer.parseInt(lsBronze.get(j).getText());
        		if(num1 + num2 ==18) {
					bronze18.add(lsCountries.get(i).getText());
            		bronze18.add(lsCountries.get(j).getText());
            	}
        	}
        }
        return bronze18;
    	}
    
   
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}