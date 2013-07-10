package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import database.DatabaseController;
import model.Pyramid;
import model.QuestionList;
import model.PyramidSquare;
import applicationLogic.ModelController;
import applicationLogic.QuestionListFactory;

public class PyramidTest {

	private QuestionListFactory questionlistFactory;

	@Test
	public void testToString() {
		String questionData = "Question one#!#1#!#1#!#Question two#!#2#!#1#!#Question three#!#3#!#2";
		questionlistFactory = new QuestionListFactory(new DatabaseController());
		QuestionList ql = questionlistFactory.createQuestionList(questionData);		
		Pyramid pyramid = new Pyramid(ql);
		
		String pyraCheck = "{ \"pyramid\":\"-1#!#-1#!#-1\"";
		
		//System.out.print(pyramid.toString());
	    //fail("pyramid.toString is outputting :" + pyramid.toString());
		assertEquals(pyramid.toString(), pyraCheck);
	}

	@Test
	public void testGet() {
		
		String questionData = "Question one#!#1#!#1#!#Question two#!#2#!#1#!#Question three#!#3#!#2";
		questionlistFactory = new QuestionListFactory(new DatabaseController());
		QuestionList ql = questionlistFactory.createQuestionList(questionData);		
		Pyramid pyramid = new Pyramid(ql);
		
		int columns = 3;
		int rows = 1;
		
        assertEquals(pyramid.getColumns(), columns);
        assertEquals(pyramid.getRows(pyramid.getColumns()), rows);
	}

}
