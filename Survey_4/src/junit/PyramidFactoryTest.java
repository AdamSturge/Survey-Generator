package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Pyramid;
import model.Question;
import model.QuestionList;

import org.junit.Before;
import org.junit.Test;

import database.DatabaseController;

import applicationLogic.PyramidFactory;

public class PyramidFactoryTest {

	private PyramidFactory pyramidFactory;
	
	@Before
	public void setUp(){
		pyramidFactory = new PyramidFactory(new DatabaseController());
	}
	
	
	@Test
	public void testCreatePyramid() {
		Question q1 = new Question("question 1",1,1,1, false);
		Question q2 = new Question("question 2",1,1,2, false);
		Question q3 = new Question("question 3",2,1,3, false);
		
		ArrayList<Question> questions = new ArrayList<Question>();
		questions.add(q1);
		questions.add(q2);
		questions.add(q3);
		
		QuestionList ql = new QuestionList(questions);
		
		Pyramid p = pyramidFactory.createPyramid(ql);
		
		//test pyramid shape is accurate (determined by questionlist)
		assertEquals(p.getColumns(),2);
		assertEquals(p.getRows(1),2);
		assertEquals(p.getRows(2),1);
		
		//test that the pyramid has no answers in it
		assertEquals(p.get(1, 1).getAnswer(),-1);
		assertEquals(p.get(1, 2).getAnswer(),-1);
		assertEquals(p.get(2, 1).getAnswer(),-1);
	}

	@Test
	public void testReadPyramid() {
		Pyramid p = pyramidFactory.readPyramid("JUnit", 1);
		
		assertEquals(p.getColumns(),2);
		assertEquals(p.getRows(1),2);
		assertEquals(p.getRows(2),1);
		
		
		assertEquals(p.get(1, 1).getAnswer(),1);
		assertEquals(p.get(1, 2).getAnswer(),2);
		assertEquals(p.get(2, 1).getAnswer(),3);
	}

}
