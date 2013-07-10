package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import model.Question;
import model.QuestionList;
import org.junit.Test;

public class QuestionListTest {
	
	



	@Test
	public void testAddQuestions() {
		ArrayList<Question> testQuestions = new ArrayList<Question>();
		Question q1 = new Question("Question1",1,1,1, false);
		Question q2 = new Question("Question2",2,2,2, false);
		Question q3 = new Question("Question3",3,3,3, false);
		testQuestions.add(q1);
		QuestionList tester = new QuestionList(testQuestions);
		testQuestions.clear();
		testQuestions.add(q2);
		tester.addQuestions(testQuestions);
		assertSame("Questions should match", q2, tester.getQuestion(2));
	}

	@Test
	public void testClearGroupMap() {
		ArrayList<Question> testQuestions = new ArrayList<Question>();
		Question q1 = new Question("Question1",1,1,1, false);
		Question q2 = new Question("Question2",2,2,2, false);
		Question q3 = new Question("Question3",3,3,3, false);
		testQuestions.add(q1);
		testQuestions.add(q2);
		testQuestions.add(q3);
		QuestionList tester = new QuestionList(testQuestions);
		tester.clearGroupMap();
		assertEquals("GroupMap keyset should be empty",0 , tester.getGroupMap().keySet().size());
		assertEquals("GroupMap entryset should be empty",0 , tester.getGroupMap().entrySet().size());
	}

	@Test
	public void testClearAnswerMap() {
		ArrayList<Question> testQuestions = new ArrayList<Question>();
		Question q1 = new Question("Question1",1,1,1, false);
		Question q2 = new Question("Question2",2,2,2, false);
		Question q3 = new Question("Question3",3,3,3, false);
		testQuestions.add(q1);
		testQuestions.add(q2);
		testQuestions.add(q3);
		QuestionList tester = new QuestionList(testQuestions);
		tester.clearAnswerMap();
		assertEquals("AnswerMap keyset should be empty",0 , tester.getAnswerMap().keySet().size());
		assertEquals("AnswerMap entryset should be empty",0 , tester.getAnswerMap().entrySet().size());
	}

	@Test
	public void testClearGroupList() {
		ArrayList<Question> testQuestions = new ArrayList<Question>();
		Question q1 = new Question("Question1",1,1,1, false);
		Question q2 = new Question("Question2",2,2,2, false);
		Question q3 = new Question("Question3",3,3,3, false);
		testQuestions.add(q1);
		testQuestions.add(q2);
		testQuestions.add(q3);
		QuestionList tester = new QuestionList(testQuestions);
		tester.clearGroupList(1);
		assertEquals("GroupList keyset should be two",2 , tester.getGroupMap().keySet().size());
		assertEquals("Grouplist entryset should be two",2 , tester.getGroupMap().entrySet().size());
	}

	@Test
	public void testClearAnswerList() {
		ArrayList<Question> testQuestions = new ArrayList<Question>();
		Question q1 = new Question("Question1",1,1,1, false);
		Question q2 = new Question("Question2",2,2,2, false);
		Question q3 = new Question("Question3",3,3,3, false);
		testQuestions.add(q1);
		testQuestions.add(q2);
		testQuestions.add(q3);
		QuestionList tester = new QuestionList(testQuestions);
		tester.clearAnswerList(1);
		assertEquals("GroupList keyset should be two",2 , tester.getAnswerMap().keySet().size());
		assertEquals("Grouplist entryset should be two",2 , tester.getAnswerMap().entrySet().size());
	}

	@Test
	public void testToString() {
		ArrayList<Question> testQuestions = new ArrayList<Question>();
		Question q1 = new Question("Question1",1,1,1, false);
		Question q2 = new Question("Question2",2,2,2, false);
		Question q3 = new Question("Question3",3,3,3, false);
		testQuestions.add(q1);
		testQuestions.add(q2);
		testQuestions.add(q3);
		QuestionList tester = new QuestionList(testQuestions);
		String test = "\"group1\":{ \"questions\":\"Question1#!#\"},\"group2\":{ \"questions\":\"Question2#!#\"},\"group3\":{ \"questions\":\"Question3#!#\"}}";
		System.out.println(test);
		assertEquals("Strings should match",test , tester.toString());
	}
}
