package week5.day2.assignments;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditLead extends LeadsSetup {

	@DataProvider(name = "TestData")
	public Object[][] getData() throws InvalidFormatException, IOException {

		String[][] data = ReadExcelForData.readExcel("EditLead");
		return data;

	}

	@Test(dataProvider = "TestData")
	public void editLead(String firstName) throws InterruptedException {

//		Click Find leads
		driver.findElement(By.xpath("//a[text()='Find Leads']")).click();

// 		Enter first name
		driver.findElement(By.xpath("(//input[@name='firstName'])[3]")).sendKeys(firstName);

// 		Click Find leads button
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

		Thread.sleep(2000);

// 		Click on first resulting lead
		String fName = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-firstName'])/a"))
				.getText();
		System.out.println("First Resulting lead: " + fName);

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-firstName'])/a")).click();

// 		Verify title of the page
		String title = driver.getTitle();
		System.out.println(title);

//		Get the Company name before edit
		String company = driver.findElement(By.id("viewLead_companyName_sp")).getText();
		company = company.replaceAll("[^a-zA-Z]", "");
//		System.out.println(company);

// 		Click Edit
		driver.findElement(By.xpath("(//a[text()='Edit'])")).click();

// 		Change the company name
		driver.findElement(By.id("updateLeadForm_companyName")).clear();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys("Amazon");

// 		Click Update
		driver.findElement(By.xpath("//input[@value='Update']")).click();

// 		Confirm the changed name appears
		String cName = driver.findElement(By.xpath("//span[@id='viewLead_companyName_sp']")).getText();
		cName = cName.replaceAll("[^a-zA-Z]", "");

		if (cName != company) {
			System.out.println(company + " has been successfully changed to " + cName);
		} else {
			System.out.println(company + " Company name has not been changed, need to look into it " + cName);
		}
	}
}