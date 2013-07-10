package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.QuestionList;

import org.junit.Before;
import org.junit.Test;

import database.DatabaseController;

import applicationLogic.QuestionListFactory;

public class QuestionListFactoryTest {

	
	private QuestionListFactory questionlistFactory;
	
	@Before
	public void initializeFactory(){
		questionlistFactory = new QuestionListFactory(new DatabaseController());
	}
	
	@Test
	public void testCreateQuestionListFrom4Arrays() {
		ArrayList<String> questionStrings = new ArrayList<String>();
		ArrayList<Integer> answerList = new ArrayList<Integer>();
		ArrayList<Integer> groupList = new ArrayList<Integer>();
		ArrayList<Integer> questionNumberList = new ArrayList<Integer>();
		ArrayList<Boolean> criticalValueList = new ArrayList<Boolean>();
		
		questionStrings.add("Question 1");
		answerList.add(1);
		groupList.add(1);
		questionNumberList.add(1);
		criticalValueList.add(false);
		
		questionStrings.add("Question 2");
		answerList.add(2);
		groupList.add(1);
		questionNumberList.add(2);
		criticalValueList.add(false);
		
		questionStrings.add("Question 3");
		answerList.add(3);
		groupList.add(2);
		questionNumberList.add(3);
		criticalValueList.add(false);
		
		QuestionList ql = questionlistFactory.createQuestionList(questionStrings, answerList, groupList, questionNumberList, criticalValueList);
		
		assertEquals(ql.getQuestion(1).getQuestion(),"Question 1");
		assertEquals(ql.getQuestion(1).getAnswer(),1);
		assertEquals(ql.getQuestion(1).getGroupNumber(),1);
		
		assertEquals(ql.getQuestion(2).getQuestion(),"Question 2");
		assertEquals(ql.getQuestion(2).getAnswer(),2);
		assertEquals(ql.getQuestion(2).getGroupNumber(),1);
		
		assertEquals(ql.getQuestion(3).getQuestion(),"Question 3");
		assertEquals(ql.getQuestion(3).getAnswer(),3);
		assertEquals(ql.getQuestion(3).getGroupNumber(),2);
		
	}

	@Test
	public void testCreateQuestionFrom3Arrays() {
		ArrayList<String> questionStrings = new ArrayList<String>();
		ArrayList<Integer> answerList = new ArrayList<Integer>();
		ArrayList<Integer> groupList = new ArrayList<Integer>();
		ArrayList<Boolean> criticalValueList = new ArrayList<Boolean>();
		
		questionStrings.add("Question 1");
		answerList.add(1);
		groupList.add(1);
		criticalValueList.add(false);
		
		questionStrings.add("Question 2");
		answerList.add(2);
		groupList.add(1);
		criticalValueList.add(false);
		
		questionStrings.add("Question 3");
		answerList.add(3);
		groupList.add(2);
		criticalValueList.add(false);
	
		QuestionList ql = questionlistFactory.createQuestionList(questionStrings, answerList, groupList, criticalValueList);
		
		assertEquals(ql.getQuestion(1).getQuestionNumber(),1);
		assertEquals(ql.getQuestion(1).getQuestion(),"Question 1");
		assertEquals(ql.getQuestion(1).getAnswer(),1);
		assertEquals(ql.getQuestion(1).getGroupNumber(),1);
		
		assertEquals(ql.getQuestion(2).getQuestionNumber(),2);
		assertEquals(ql.getQuestion(2).getQuestion(),"Question 2");
		assertEquals(ql.getQuestion(2).getAnswer(),2);
		assertEquals(ql.getQuestion(2).getGroupNumber(),1);
		
		assertEquals(ql.getQuestion(3).getQuestionNumber(),3);
		assertEquals(ql.getQuestion(3).getQuestion(),"Question 3");
		assertEquals(ql.getQuestion(3).getAnswer(),3);
		assertEquals(ql.getQuestion(3).getGroupNumber(),2);
	}

	@Test
	public void testCreateQuestionListFromString() {
		String questionData = "Question 1#!#1#!#1#!#false#!#Question 2#!#2#!#1#!#false#!#Question 3#!#3#!#2#!#false";
		QuestionList ql = questionlistFactory.createQuestionList(questionData);
		
		assertEquals(ql.getQuestion(1).getQuestion(),"Question 1");
		assertEquals(ql.getQuestion(1).getAnswer(),1);
		assertEquals(ql.getQuestion(1).getGroupNumber(),1);
		
		assertEquals(ql.getQuestion(2).getQuestion(),"Question 2");
		assertEquals(ql.getQuestion(2).getAnswer(),2);
		assertEquals(ql.getQuestion(2).getGroupNumber(),1);
		
		assertEquals(ql.getQuestion(3).getQuestion(),"Question 3");
		assertEquals(ql.getQuestion(3).getAnswer(),3);
		assertEquals(ql.getQuestion(3).getGroupNumber(),2);
	}

	@Test
	public void testReadQuestionList() {
		QuestionList ql = questionlistFactory.readQuestionList(1); //there is test data in the database under SID 1
		
		assertEquals(ql.getQuestion(1).getQuestion(),"Question 1");
		assertEquals(ql.getQuestion(1).getAnswer(),1);
		assertEquals(ql.getQuestion(1).getGroupNumber(),1);
		
		assertEquals(ql.getQuestion(2).getQuestion(),"Question 2");
		assertEquals(ql.getQuestion(2).getAnswer(),2);
		assertEquals(ql.getQuestion(2).getGroupNumber(),1);
		
		assertEquals(ql.getQuestion(3).getQuestion(),"Question 3");
		assertEquals(ql.getQuestion(3).getAnswer(),3);
		assertEquals(ql.getQuestion(3).getGroupNumber(),2);
	}

}
