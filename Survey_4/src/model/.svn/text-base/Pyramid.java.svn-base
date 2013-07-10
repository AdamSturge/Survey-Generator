package model;


import java.util.ArrayList;
import java.util.HashMap;


import applicationLogic.ModelController;

public class Pyramid  {

	private HashMap<Integer,HashMap<Integer,PyramidSquare>> pyramid;
	//private QuestionList questionList;
	private HashMap<Integer,ArrayList<Question>> answerMap;

	public Pyramid(QuestionList ql){
		pyramid = new HashMap<Integer,HashMap<Integer,PyramidSquare>>();
		answerMap = ql.getAnswerMap();
		for(Integer answer: answerMap.keySet()){
			ArrayList<Question> answerList = answerMap.get(answer);
			for(int row = 0; row < answerList.size(); ++row) {
				PyramidSquare currentSquare = new PyramidSquare(answer, row);
				HashMap<Integer,PyramidSquare> rowMap = pyramid.get(answer);
				if(rowMap == null){
					rowMap =  new HashMap<Integer,PyramidSquare>();
					rowMap.put(row+1,currentSquare);
				}
				else{
					rowMap.put(row+1, currentSquare);
				}

				pyramid.put(answer,rowMap);
			}
		}
	}

	public int getColumns(){
		int col = -1;
		for(Integer i: answerMap.keySet()) {
			if (i > col){
				col = i;
			}
		}
		return col;
	}

	public int getRows(int col){
		int rows = 0;
		ArrayList<Question> answerList = answerMap.get(col);
		if(answerList != null){
			rows = answerList.size();
		}

		return rows;
	}

	public Pyramid(String pyramidString){
		answerMap = new HashMap<Integer,ArrayList<Question>>();
		pyramid = new HashMap<Integer, HashMap<Integer,PyramidSquare>>();
		
		System.out.println("@Pyramid constructor: pyramidString="+pyramidString);
		
		//Split the data into pyramid segment and critical value segment
		
		
		//Perform operations on Pyramid segment
		String[] columns = pyramidString.split(ModelController.dataSplitter);
		PyramidSquare currentSquare = null;
		String answerString = null;
		for(int colNumber = 0; colNumber < columns.length; ++colNumber){
			String[] answers = columns[colNumber].split(",");
			for(int rowNumber = 0; rowNumber< answers.length; ++rowNumber){
				answerString = answers[rowNumber];
				if(!answerString.equals("null")){
					System.out.println("@Pyramid constructor:  answerString="+answerString);
					int answer = Integer.parseInt(answerString);
					if(answerMap.containsKey(colNumber+1)){
						ArrayList<Question> list = answerMap.get(colNumber+1);
						list.add(new Question("",colNumber+1,0,0,false));
						answerMap.put(colNumber+1, list);
					}else {
						ArrayList<Question> list = new ArrayList<Question>();
						list.add(new Question("",colNumber+1,0,0,false));
						answerMap.put(colNumber+1,list);
					}
					currentSquare = new PyramidSquare(colNumber+1,rowNumber+1,answer);
					HashMap<Integer,PyramidSquare> rowMap = pyramid.get(colNumber+1);
					if(rowMap == null){
						rowMap =  new HashMap<Integer,PyramidSquare>();
						rowMap.put(rowNumber+1,currentSquare);
					}
					else{
						rowMap.put(rowNumber+1, currentSquare);
					}

					pyramid.put(colNumber+1,rowMap);
				} 

			}
		}
	}

	/**
	 * String protocol: Columns are separated by #!#. Columns are listed in order.
	 * Each pyramid square in a column is represented by the question number it stores,
	 * with -1 indicating an empty square. The pyramid squares are separated by commas
	 */

	public String toString(){

		String output = "{ \"pyramid\":\"" ;
		int answer;
		int maxKey = 0;
		for(Integer i: answerMap.keySet()) {
			if (i > maxKey){
				maxKey = i;
			}
		}
		ArrayList<Question> answerList;
		for(int i = 1; i <= maxKey; ++i ) {
			answerList = answerMap.get(i);
			if(answerList != null){
				for(int j = 1; j < answerList.size()+1; j++){
					PyramidSquare ps = this.get(i,j);
					answer = ps.getAnswer();
					if(j != answerList.size())
						output +=  answer + ",";
					else
						output += answer;
				}
				if(i != maxKey)
					output += "#!#";

			}
			else
				output += "null#!#";
		}

		output +=  "\"";
		return output;

		/** OLD CODE
		for(Integer i : answerMap.keySet()){
			answerList = answerMap.get(i);
			output += "\"col" + i + "\":\""+ answerList.size() + "\",";
		}
		output = output.substring(0,output.length()-1); //remove last comma
		output +=  "}";
		return output;
		 */
		/** MORE OLD CODE FOR REFERENCE
		 * 		for(int i = 0; i <= maxKey; ++i ) {
			answerList = answerMap.get(i);
			if(answerList != null){
				output +=  answerList.size() + "#!#";
			}else {
				output += 0 + "#!#";
			}
		}
		 */

	}
	public PyramidSquare get(int i, int j){
		return pyramid.get(i).get(j);
	}


	//finds the column that the given question number is stored in
	//returns -1 if it cannot be found
	public int getColumn(int questionNumber){
		int colNumber = this.getColumns();
		int rowNumber;
		for(int i = 1; i <= colNumber; ++i){
			rowNumber = this.getRows(i);
			for(int j = 1; j<= rowNumber; ++j){
				if(this.get(i, j).getAnswer() == questionNumber){
					return i;
				}
			}
		}
		return -1;
	}

}
