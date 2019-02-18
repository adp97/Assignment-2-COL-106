public class SearchTree {
	protected class Pair{

		String name;
		//int level;
		Pair left;
		Pair right;
		Pair parent;
		public Pair(String n){

			name = n ;
			//level = 0;
			left = null;
			right = null;
			parent = null;
		}
		public Pair(){
			name = null ;
			//level = 0;
			left = null;
			right = null;
			parent = null;
		}
	}

	public static Pair SearchTreeroot;

	public void SearchTree(){

		SearchTreeroot = null;
	}
	
	/*public void create(String p){

		String na = "mike";
		int i = p.compareTo(na);
		System.out.println(i);
	}
*/
	public void insert(String p){  
       
        if(isExisting(p)){
       
        	return;
        }
       
		Pair temp = SearchTreeroot;
		Pair prev = null;
		if(temp == null){
			SearchTreeroot = new Pair(p);
			SearchTreeroot.parent = null;
			return;
		}

		while(temp != null){
			
			if(p.compareTo(temp.name) < 0){
				prev = temp;
				temp = temp.left;
				if(temp == null){
					temp = new Pair(p);
					temp.parent = prev;
					prev.left = temp;
					return;
				}

			}	

			if(p.compareTo(temp.name) > 0){
				prev = temp;
				temp = temp.right;
				if(temp == null){
					temp = new Pair(p);
					temp.parent = prev;
					prev.right = temp;
					return;
				}
			}
		}
	}

	public boolean isExisting(String p){
		
		Pair temp = search(p);
		if(temp == null){
			return(false);
		}
		else if(temp != null){
		if(temp.name == p){
			return(true);
		}
	}

		return(false);

	}

	public boolean isLeaf(Pair p){

		if(hasLeft(p) == false && hasRight(p) == false)
			return(true);
		else
			return(false);
	}

	public void delete(String p) {
		
		Pair temp = search(p);
		if(temp == SearchTreeroot)
			return;
		delete_subfunction(temp);
		if(hasLeft(temp) == true && hasRight(temp) == true){

			Pair minNode = rightSubTreeMinValue(temp);
			temp.name = minNode.name;
			delete_subfunction(minNode);
		}
	}

	public void delete_subfunction(Pair p){

		if(hasLeft(p) == false && hasRight(p) == false){
			if(p.parent.left == p)
				p.parent.left = null;
			else if(p.parent.right == p)
				p.parent.right = null;
			
			
			return;
		}
		if(hasLeft(p) == false){
			p.parent.right = p.right;	
			
			return;
		}
		if(hasRight(p) == false){
			p.parent.left = p.left;	
			
			return;
		}

	}

	public boolean isEmpty() {

		return(SearchTreeroot == null);
	}

	public boolean hasLeft(Pair p){

		if(p.left != null)
			return(true);
		else
			return(false);
	}

	public boolean hasRight(Pair p){

		if(p.right != null)
			return(true);
		else
			return(false);
	}

	public Pair search(String p) throws NullPointerException{

		Pair temp = SearchTreeroot;
		if(temp == null){
			
			return(null);
		}
		while(temp != null){
			if(p.compareTo(temp.name) == 0){
			
				return(temp);
			}

			if(p.compareTo(temp.name) < 0){
			
				temp = temp.left;
				if(temp == null)
					return(temp);
			}

			if(p.compareTo(temp.name) > 0){
			
				temp = temp.right;
				if(temp == null)
					return(temp);	
			}

		}
		return(temp);
	}

	public Pair rightSubTreeMinValue(Pair p){

		p = p.right;
		if(hasRight(p) == false && hasLeft(p) == false)
			return(p);
		if(p.left == null && hasRight(p) == true)
			return(p);
		while(p.left != null){

			p = p.left;			
		}
		return(p);
	}

	public void inorderTraversal(Pair root) {

		if(root == null)
			return;
		inorderTraversal(root.left);
		System.out.println(root.name);
		inorderTraversal(root.right);

	}

/*	public static void main(String[] args){
		SearchTree sr = new SearchTree();
		
		try{
			sr.insert("mark");
			sr.insert("aditya");
			sr.insert("mike");
			sr.insert("shub");	
			//sr.insert("aditya");
			//sr.inorderTraversal(SearchTreeroot);
			sr.delete("mark");
			sr.delete("mike");
			sr.delete("aditya");
			
		}
		catch(NullPointerException e){

			System.out.println("Problem");
		}
		
		sr.inorderTraversal(SearchTreeroot);


		InputStream file = null; 
		Reader r = null; 
		BufferedReader br = null; 
		int num_commands=0;
		long line_no = 0;
		try {
    			file = new FileInputStream(args[0]);
    			r = new InputStreamReader(file, "UTF-8"); 
    			br = new BufferedReader(r);
    			String text = br.readLine();
        		int num_employees = Integer.parseInt(text);
        		text = br.readLine();
        		String[] ceoAddition = text.split(" ");
        		System.out.println("Step : 0");
        		sr.insert(ceoAddition[1]);
        		sr.insert(ceoAddition[0]);
	        		for(int i = 1; i< num_employees ; i++){

	        			text = br.readLine();
	        			line_no++;
	        			if(i == num_employees-1){
	        				num_commands = Integer.parseInt(text);
	        				line_no++;
	        				break;
	        			}
	        
	        			String[] parts  = text.split(" ");
	        			System.out.println("Step : " + i);
	        			sr.insert(parts[0]);
	        			//tr.AddEmployee(parts[0], parts[1]);
	        		}
	        		System.out.println("******************************");
	        		for(int j= 0; j<num_commands; j++){
	        			text= br.readLine();
	        			line_no++;
	        			String[] partss = text.split(" ");
	        			if(partss.length == 1){
	        				System.out.println("Do thing 3-------------------------------");
	        				sr.inorderTraversal(SearchTreeroot);
	        			}

	        			else if(partss.length == 3){

	        				if(Integer.parseInt(partss[0]) == 0){
	        					//System.out.println("Do thing 0");
	        					//insert AddEmployee(partss[1] , partss[2]) function here
	        					sr.insert(partss[1]);
	        				}
	        				if(Integer.parseInt(partss[0]) == 1){
	        					//System.out.println("Do thing 1");
	        					//insert DeleteEmployee(partss[1] , partss[2]) function here
	        					sr.delete(partss[1]);
	        				}
	        				if(Integer.parseInt(partss[0]) == 2){}
	        					//System.out.println("Do thing 2");
	        					//insert LowestCommonBoss(parts[1] , parts[2]) function here	
	        			}
	        		}
			}
			
		catch (Exception e)
			{
    		System.out.println("Problem");
			}
		finally {
			
    		if (br != null) { try { br.close(); } catch(Throwable t) { } }
    		if (r != null) { try { r.close(); } catch(Throwable t) { } }
    		if (file != null) { try { file.close(); } catch(Throwable t) { } }
		}

	}
}
*/
}
class ExistingEmployeeError extends Exception {

	public void ExistingEmployeeError() {
		

	}
}
