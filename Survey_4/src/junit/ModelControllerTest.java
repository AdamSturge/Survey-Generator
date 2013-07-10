package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import applicationLogic.ModelController;

public class ModelControllerTest {

	private ModelController mController;
	
	@Before
	public void setUp(){
		mController = new ModelController();
	}
	
	@Test
	public void testFetchSurvey() {
		String survey = mController.fetchSurvey(1, "JUnit", false);
		String compareTo = "{\"survey\": { \"pyramid\":\"1,2#!#3\",\"group1\":{ \"questions\":\"Question 1#!#Question 2#!#\"},\"group2\":{ \"questions\":\"Question 3#!#\"}}}";
		
		assertEquals(survey,compareTo);
	}

	@Test
	public void testWriteToAdminSurveyLookup() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdminSurveyLookup() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteToUserSurveyLookup() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserSurveyLookup() {
		fail("Not yet implemented");
	}

	@Test
	public void testWritePyramid() {
		fail("Not yet implemented");
	}

	@Test
	public void testWriteQuestionList() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateSurvey() {
		fail("Not yet implemented");
	}

	@Test
	public void testSavePyramid() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveQuestionList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPreview() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPreview() {
		fail("Not yet implemented");
	}

	@Test
	public void testPreview() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateSurveyString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteSurvey() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchMatches() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserSubmit() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetOnlineStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchQuestionList() {
		fail("Not yet implemented");
	}

}
