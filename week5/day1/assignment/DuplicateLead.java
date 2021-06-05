package week5.day1.assignment;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class DuplicateLead extends Leads {

	@Test(dependsOnMethods = "week5.day1.assignment.CreateLead.createLead")
	public void duplicateLead() throws InterruptedException {

//		Click Find leads
		driver.findElement(By.xpath("//a[text()='Find Leads']")).click();

//		Click on Email
		driver.findElement(By.xpath("//span[text()='Email']")).click();

//		Enter Email
		driver.findElement(By.name("emailAddress")).sendKeys("durgatest@gmail.com");

//		Click find leads button
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

		Thread.sleep(2000);

//		Capture name of First Resulting lead
		String firstName = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-firstName'])/a"))
				.getText();
		System.out.println("First Resulting lead: " + firstName);

//		Click First Resulting lead
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-firstName'])/a")).click();

//		Click Duplicate Lead
		driver.findElement(By.xpath("(//a[text()='Duplicate Lead'])")).click();

//		Verify the title as 'Duplicate Lead'
		String getTitle = driver.findElement(By.xpath("(//div[@id='sectionHeaderTitle_leads'])")).getText();
		if (getTitle.equalsIgnoreCase("Duplicate Lead")) {
			System.out.println("Title verification success");
		} else {
			System.out.println("Incorrect Title. Have a check on it");
		}

//		Click Create Lead
		driver.findElement(By.className("smallSubmit")).click();

//		Confirm the duplicated lead name is same as captured name
		String duplicatedFname = driver.findElement(By.id("viewLead_firstName_sp")).getText();
		// System.out.println(duplicatedFname);

		if (firstName.equalsIgnoreCase(duplicatedFname)) {
			System.out.println("Duplicated lead name: " + firstName + "is same as captured name: " + duplicatedFname);
		} else {
			System.err.println("Name not matched !! Have a check on it");
		}

	}
}