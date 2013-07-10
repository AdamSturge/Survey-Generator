package junit;

import static org.junit.Assert.*;

import model.Pyramid;
import model.QuestionList;

import org.junit.Before;
import org.junit.Test;

import database.DatabaseController;

import applicationLogic.ProfileMatcher;
import applicationLogic.PyramidFactory;
import applicationLogic.QuestionListFactory;

public class ProfileMatcherTest {

	private ProfileMatcher profileMatcher;
	
	@Before
	public void startUp(){
		profileMatcher = new ProfileMatcher(new DatabaseController());
		
	}
	
	@Test
	public void testComputeMatch() {
		int match = profileMatcher.computeMatch("JUnit", 1);
		assertEquals(match,2); //JUnit's pyramid and perfect answers differ by 2
	}

}
