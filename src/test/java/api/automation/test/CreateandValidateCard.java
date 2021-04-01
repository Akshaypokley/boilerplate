package api.automation.test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.*;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import ui.automation.pages.Cardspage;
import ui.automation.pages.LogSignUp;

public class CreateandValidateCard {
	Object listid;
	static WebDriver driver;
	static ExtentReports extent ;
	static int i=1;
	static ExtentTest extentTest;
	static LogSignUp logsignup;
	static Cardspage cardspage;
	 HSSFSheet sh;
	static int cloumCount;
	static int addtaskop=1;
	@Test(dataProvider = "hybridData")
	public void cardcreationandvalidation(String KeyValue,String TokenKey
			,String BoardID,String BoardNm,String ListName,String CardName)
	{
		RestAssured.baseURI="https://api.trello.com";
		
		//GetBoard Details
		
		String getResponce = given().log().all().queryParam("key", KeyValue).queryParam("token",TokenKey).header("Content-Type","application/json")
				.when().get("/1/boards/"+BoardID+"")
				.then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath getjson=new JsonPath(getResponce);
		
		String BoardName=getjson.get("name");
		Assert.assertEquals(BoardName,BoardNm);
		
		//GetBoardList Details
		
		Response getBoardListResponce = given().log().all().queryParam("key", KeyValue).queryParam("token",TokenKey).header("Content-Type","application/json")
				.when().get("/1/boards/"+BoardID+"/lists")
				.then().log().all().assertThat().statusCode(200).extract().response();
		JSONArray array = new JSONArray(getBoardListResponce.getBody().asString());
		
		for(int i=0; i<array.length();i++) {
			//System.out.println(array.get(i));
			
			JSONObject obj = array.getJSONObject(i);
			Object listname=obj.get("name");
			if(listname.equals(ListName))
			{
				System.out.println("PASSED");
				
				listid=obj.get("id");
				System.out.println(listid);
				
			}
		}
		
		//Add Card Details
		
		String AddCard=given().log().all().queryParam("key", KeyValue)
				.queryParam("token",TokenKey)
				.queryParam("idList", listid).queryParam("name",CardName).header("Content-Type","application/json")
				.when().post("/1/cards").then().assertThat().statusCode(200)
				.extract().asString();
		
		//Validate Card Details
		
		Response ValiadtedCard = given().log().all().queryParam("key", KeyValue).queryParam("token",TokenKey).header("Content-Type","application/json")
				.when().get("/1/boards/"+BoardID+"/cards")
				.then().log().all().assertThat().statusCode(200).extract().response();
		JSONArray array2 = new JSONArray(ValiadtedCard.getBody().asString());
		
		for(int i=0; i<array2.length();i++) {
			//System.out.println(array.get(i));
			
			JSONObject obj = array2.getJSONObject(i);
			Object Cardname=obj.get("name");
			
			if(Cardname.equals(CardName))
			{
				System.out.println("PASSED");
				
				
			}
		}
	
	}

	@DataProvider(name = "hybridData")
	public  Object[][] getDataFromDataprovider() throws IOException {
		Object[][] object = null;
		FileInputStream fis = new FileInputStream("ExternalSrcDir/APITestData.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		sh = wb.getSheet("APIData");
		int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();
		System.out.println(rowCount);

		object = new Object[rowCount][6];
		for (int i = 0; i < rowCount; i++) {

			HSSFRow row = sh.getRow(i+1);


			for (int j = 0; j < row.getLastCellNum(); j++) {
				// System.out.println(row.getCell(j).toString());
				object[i][j] = row.getCell(j).toString();

			}


		}
		return object;


	}
	}
		
		
