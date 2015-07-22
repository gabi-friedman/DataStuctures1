package blobsRecursive;

import java.util.Random;
import java.util.Stack;
import java.util.Vector;

public class GridRecursiveStack<E> {
	//a Vector of Vectors of Cells

	int rows;
	int cols;
	int numBlobs = 0;
	private Vector<Vector<Cell<E>>> cells;

	public GridRecursiveStack(int rows, int columns){
		this.rows = rows;
		this.cols = columns;

		cells = new Vector<Vector <Cell<E>>>();

		//initialize each cell of the grid
		for (int row = 0;row<rows;row++){
			//set up a Vector that will represent a new row in a two dimensional grid
			Vector<Cell<E>> tempVector = new Vector<Cell<E>>();

			for (int col=0;col<columns;col++){
				tempVector.add(new Cell<E>(row,col)); //add a Cell to this Vector
			}
			//add this new vector to the Vector of Vectors 
			cells.add(tempVector);
		}

	}

	@SuppressWarnings("unchecked")
	public void fillAllNeighbors(){
		Cell<E>[] temp;

		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols; col++){
				temp = cells.get(row).get(col).getArray();
				if(row+1 < rows){//HERE- row+1 instead of row & rows instead of 0
					temp[0] = cells.get(row+1).get(col  );//top
				}
				else{
					temp[0] = null;
				}
				if(col+1 < cols){//HERE- col+1 instead of col
					temp[1] = cells.get(row  ).get(col+1);//right
				}
				else{
					temp[1] = null;
				}
				if(row > 0){
					temp[2] = cells.get(row-1).get(col  );//left
				}
				else{
					temp[2] = null;
				}
				if(col > 0){
					temp[3] = cells.get(row  ).get(col-1);//down
				}
				else{
					temp[3] = null;
				}
			}
		}
	}

	public void setGrid(int percentage, E value){
		Random randomGenerator = new Random(System.currentTimeMillis());
		int randomNum;

		for(int row = 0;row< cells.size();row++){
			for (int col=0;col < cells.get(row).size();col++){
				randomNum = randomGenerator.nextInt(100);
				if (randomNum < percentage){
					//first get() , gets access to the Vector on a particular row
					//second get() , gets access to a particular Cell in the Vector on a particular row
					cells.get(row).get(col).setData(value);
				}
			}
		}
	}

	public int countBlobs(E value ){

		int count =0;

		for (int row =0;row < cells.size();row++){
			for (int col=0;col< cells.get(row).size();col++){
				Cell<E>startCell = cells.get(row).get(col);
				System.out.println("starting at cell [" + row + "][" + col + "]");
				if (!startCell.isVisited() && startCell.hasData()){
					count++;
					//mark the blob connected to starting cell
					markBlobsStack(startCell); 
				}
			}
		}
		return count;
	}

	public void markBlob(Cell<E> currentCell){
		//mark the current cell as visited	
		System.out.println("current cell " + currentCell.getRow() + " " + currentCell.getCol());
		//went this route already ,no need to explore it again
		if (currentCell.isVisited()){
			return;
		}
		currentCell.setVisited();
		//base case / anchor case
		if (!currentCell.hasData() ){
			return;  //end of connecting blob - hit empty space
		}
		//traverse up
		if (currentCell.getRow() > 0){ //end of grid
			//move up one row and mark blob starting there
			System.out.println("moved up from cell " + currentCell + " " + currentCell.getRow() + " "+ currentCell.getCol());
			markBlob( cells.get(currentCell.getRow()-1).get(currentCell.getCol()));
		}

		//traverse down
		if (currentCell.getRow()< cells.size() -1){ //move down one row
			System.out.println("moved down from cell " + currentCell + " " + currentCell.getRow() + " " + currentCell.getCol());
			markBlob( cells.get(currentCell.getRow()+1).get(currentCell.getCol()));
		}
		//traverse left
		if (currentCell.getCol() > 0){ //end of grid
			//move over left one column
			System.out.println("moved left from cell " + currentCell + " " + currentCell.getRow() + " " + currentCell.getCol() );
			markBlob( cells.get(currentCell.getRow()).get(currentCell.getCol()-1));
		}

		//traverse right

		if (currentCell.getCol() < cells.get(currentCell.getRow()).size() -1){ 

			//move over right one column
			System.out.println("moved right from cell " + currentCell + " " + currentCell.getRow() + " " + currentCell.getCol());
			markBlob( cells.get(currentCell.getRow()).get(currentCell.getCol()+ 1));
		}


	}

	public String toString(){
		StringBuffer buffer = new StringBuffer();
		for(int row = 0;row< cells.size();row++){buffer.append("\n");
		for (int col=0;col < cells.get(row).size();col++){



			buffer.append(" " + cells.get(row).get(col).toString());
		}
		}
		return buffer.toString();
	}

	public void markBlobsStack(Cell<E> cur){
		Cell<E> temp;
		Cell<E> nbrs[];
		Stack<Cell<E>> stack = new Stack<Cell<E>>();
		Stack<Cell<E>> blob = new Stack<Cell<E>>();

		//send in the cell that was found w data
		//getneighbors
		//check if neighbors have data
		//if yes add to stack
		//while stack ! empty
		//get next
		//mark visited
		//get neighbors
		//add cells w data to stack

		fillAllNeighbors();

		stack.push(cur);
		while(!stack.isEmpty()){
			temp = stack.pop();
			temp.setVisited();
			nbrs = temp.getArray();
			for(int i = 0; i < nbrs.length; i++){
				if(nbrs[i]!=null && !nbrs[i].isVisited()){//HERE- Switched order of statements
					if(nbrs[i].hasData()){
						stack.push(nbrs[i]);
					}
				}
			}
			blob.push(temp);
		}

		while(!blob.isEmpty()){
			System.out.println(blob.pop());
		}



	}

	static public void main(String []  args){
		Character character = new Character('X');
		GridRecursiveStack<Character> theGrid = new GridRecursiveStack<Character>(5,5);
		theGrid.setGrid(40, 'X');
		System.out.println(theGrid.toString());
		System.out.println(theGrid.countBlobs(character));
		System.out.println(theGrid);
	}
}
