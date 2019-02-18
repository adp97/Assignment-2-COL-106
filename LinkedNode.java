class LinkedNode extends Company{

		EmpNode data;
		LinkedNode next;

		LinkedNode() {

			data = null;
			next = null;
		}

		LinkedNode(EmpNode child){

			data = child;
			next = null;
			
		}
		public LinkedNode head;
	

		public  void insert(EmpNode child){
			LinkedNode child_node = new LinkedNode(child);
			
			if(head == null)
				head = new LinkedNode(child);

			child_node.next = head;
			head = child_node;
		}

		public void enqueueChildren(Queue q){

			LinkedNode temp = head;
			while(temp.next != null){
				q.enqueue(temp.data);
				
				temp = temp.next;
			}
		}

		public boolean haveChildren(){

			LinkedNode temp = head;
			if(temp == null)
				return(false);
			
			return(true);
		}
		public void printchildren() throws NullPointerException{

			LinkedNode temp = head;
			
			while(temp.next != null){

				System.out.println(temp.data.name + " " + temp.data.level);
				temp = temp.next;
			}
		}

		public void viewTop() throws NullPointerException{

			//System.out.println(head.data.name + " " + head.data.level +  " " + head.data.parent.name);
			//return(head.data);
		}

		public void remove(EmpNode child){

			LinkedNode temp = head;
			LinkedNode prev = null;
			if(temp.data == child){
				head = temp.next; 
				temp.data = null;
				return;
			}
			while(temp != null){

				if(temp.data == child){
					prev.next = temp.next;
					
				}
				
				prev  = temp;
				temp = temp.next;
			}
		}
	}