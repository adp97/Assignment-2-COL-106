import java.io.*;
import java.lang.String.*;

public class Company extends SearchTree{

	public class EmpNode extends LinkedNode {

		String name;
		int level;
		LinkedNode children;
		EmpNode parent;
		public EmpNode(String n){

			name = n;
			level = 0;
			parent = null;
			children = new LinkedNode();
		}
		public EmpNode(String n , int l){
			name = n;
			level = l;
			parent = null;
			children = new LinkedNode();

		}
	}

	Company(){
		root = null;		
		q_print = new Queue();
		header = q_print.new QueueNode();
		que = new Queue();
		pair = new SearchTree();
		empAssign = null;
		empDel = null;
		emp1 = null;
		emp2 = null;
		empPair = pair.new Pair();
	}

	public EmpNode emp1;
	public EmpNode emp2;
	public EmpNode empDel;
	public EmpNode empAssign;
	public EmpNode root;
	public SearchTree pair;	
	public Queue q_print;
	public Queue que;
	public SearchTree.Pair empPair;
	public Queue.QueueNode header;
	
	public class Queue {

		public class QueueNode { 
	   		EmpNode data; 
	    	QueueNode prev; 
	  		public QueueNode(){
	  			data = null;
	  			prev = null;
	  		}
	    	public QueueNode(EmpNode d) { 

	      		data = d; 
	       		prev = null; 
	    	} 
		} 
			public QueueNode queuehead;
			public QueueNode tail;
			public Queue(){

				queuehead = null;
				tail = null;
			}

			public void enqueue(EmpNode n){
				if(tail == null){
					tail = new QueueNode(n);
					queuehead = tail;
					
					return;
				}

				QueueNode new_node = new QueueNode(n);
				tail.prev = new_node;
				tail = new_node;
				new_node.prev = null;
				

			}

			public void dequeue(){

				if(isEmpty()){

					return;
				}
				System.out.println("Employee name: " + queuehead.data.name + " Level: " + queuehead.data.level);
				queuehead = queuehead.prev ;
			}

			public boolean isEmpty(){

				if(queuehead == null)
					return(true);
				return(false);
			}
	}

	public void DeleteEmployee(String s , String s_dash) throws DeletionError{
		if(searchEmp(s_dash) == false || searchEmp(s) == false)
			throw new DeletionError();

		empDel = Empsearcher(s);	
		empAssign = Empsearcher(s_dash);
		//traverse through the children of node S
		if(empAssign == null || empDel == null)
			throw new DeletionError();
		if(empDel.level != empAssign.level){
			throw new DeletionError();
		}
		pair.delete(s);
		if(empDel.children.head == null){

			empDel.parent.children.remove(empDel);
			empDel.parent = null;
			empDel.children = null;
			System.out.println("Removed " + empDel.name + " assigned juniors to " + empAssign.name);
		}
		else{
			LinkedNode temp = empDel.children.head;
			while(temp.next != null){
				empAssign.children.insert(temp.data);
				temp.data.parent = empAssign;
				temp = temp.next;
			}
			empDel.parent.children.remove(empDel);
			empDel.parent = null;
			//empDel.children = null;
			System.out.println("Removed " + empDel.name + " assigned juniors to " + empAssign.name);
		}
	}

	public void AddEmployee(String s , String s_dash) throws AdditionError{
		pair.insert(s);
		EmpNode temp = root;
		if(temp == null){
			pair.insert(s_dash);
			root = new EmpNode(s_dash , 1);			
			q_print.enqueue(root);			
			EmpNode employee = new EmpNode(s);			
			q_print.enqueue(employee);			
			int level = root.level;
			employee.level = level + 1;
			employee.parent = root;
			root.children.insert(employee);
			return;
		}

		EmpNode employee = new EmpNode(s);		
		q_print.enqueue(employee);	
		EmpNode boss = Empsearcher(s_dash);
		if(boss == null)
			throw new AdditionError();
		int level = boss.level;
		employee.level = level + 1;
		employee.parent = boss;
		boss.children.insert(employee);
	}

	public EmpNode Empsearcher(String p) throws NullPointerException{
		header = q_print.queuehead; 
		while(header != null){
			if(p.compareTo(header.data.name) == 0){
				return(header.data);
			}
			else{
				header = header.prev;
			}	
		}
		return(null);  //replace this with the search funtion in SearchTree
	}

	public boolean searchEmp(String p){

		empPair = pair.search(p);
		if(p.compareTo(empPair.name) == 0)
			return(true);
		return(false);
	}
	public void LowestCommonBoss(String s , String s_dash){

		emp1 = Empsearcher(s);	
		emp2 = Empsearcher(s_dash);
		int diff = emp1.level - emp2.level;

		if(diff>0){

			while(diff != 0){

				emp1 = emp1.parent;
				diff--;
			}
		}
		else if(diff<0){
			
			while(diff !=0){
				emp2 = emp2.parent;
				diff++;
			}	
		}	

		while(emp1 != root && emp2 != root) {
         
	 	 	 if(emp1.parent == emp2.parent){
				System.out.println(emp1.parent.name);
				return;
			}	
			emp1 = emp1.parent;
			emp2 = emp2.parent;
			
			}
	
	}

	public void printCompanyTree(){
		EmpNode temp = root;
	
		que.enqueue(temp);
		
		while(que.isEmpty() == false){
			if(que.queuehead.data.children.haveChildren() == false){
				que.dequeue();		
			}
			else{
				que.queuehead.data.children.enqueueChildren(que);
				que.dequeue();
			}
		}
		que = new Queue();
	}

	public static void main(String[] args){
		Company tree = new Company();
		
		InputStream file = null; 
		Reader r = null; 
		BufferedReader br = null; 
		int num_commands=0;
		long line_no = 0;
		try
		{	
			file = new FileInputStream(args[0]);
			r = new InputStreamReader(file, "UTF-8"); 
			br = new BufferedReader(r);
			String text = br.readLine();
    		int num_employees = Integer.parseInt(text);
  			text = br.readLine();
  			String[] addition = text.split(" ");
  			tree.AddEmployee(addition[0] , addition[1]);
  			for(int i = 0; i< num_employees-2; i++){

	        			text = br.readLine();
	        			line_no++;
	        			if(i == num_employees-1){

	        				num_commands = Integer.parseInt(text);
	        				line_no++;
	        				break;
	        			}
	        			
	        			addition = text.split(" ");
	        			
	        			tree.AddEmployee(addition[0] , addition[1]);
	     
	        }
	      	tree.searchEmp("UwDLEQu_6_720_6");
	        text = br.readLine();
	        num_commands = Integer.parseInt(text);
	   		System.out.println(num_commands);
	        for(int j= 0; j<num_commands; j++){
	        			text= br.readLine();
	        			line_no++;
	        			String[] partss = text.split(" ");
	        			if(partss.length == 1){
	        				tree.printCompanyTree();
	        			}

	        			else if(partss.length == 3){

	        				if(Integer.parseInt(partss[0]) == 0){
	        					tree.AddEmployee(partss[1] , partss[2]);	        					
	        					
	        				}
	        				if(Integer.parseInt(partss[0]) == 1){
	        				
	        					tree.DeleteEmployee(partss[1] , partss[2]);
	        					
	        				}
	        				if(Integer.parseInt(partss[0]) == 2){
	        					tree.LowestCommonBoss(partss[1] , partss[2]);	
	        			
	        				}
	        			}
	        		}
		}
		catch(DeletionError e){
			e = new DeletionError();
		}
		catch(AdditionError e){
			e = new AdditionError();
		}
		catch(Exception e){
			System.out.println("There was an error");

		}

		finally {
			
    		if (br != null) { try { br.close(); } catch(Throwable t) { } }
    		if (r != null) { try { r.close(); } catch(Throwable t) { } }
    		if (file != null) { try { file.close(); } catch(Throwable t) { } }
		}
	}
}

class DeletionError extends Exception{
	protected DeletionError(){
		System.out.println("Deletion Error");
	}
}

class AdditionError extends Exception{
	protected AdditionError(){
		System.out.println("Addition error");
	}
}