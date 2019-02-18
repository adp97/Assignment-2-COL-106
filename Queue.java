public class Queue {

	class QueueNode { 
   		String data; 
    	QueueNode prev; 
  
    	public QueueNode(String d) { 
        data = d; 
        prev = null; 
    	} 
	} 
		public QueueNode head;
		public QueueNode tail;

		public Queue(){

			head = null;
			tail = null;
		}

		public void enqueue(String n){

			if(tail == null){
				tail = new QueueNode(n);
				head = tail;
				//System.out.println("Done");
				return;
			}

			QueueNode new_node = new QueueNode(n);
			tail.prev = new_node;
			tail = new_node;
			new_node.prev = null;
			//System.out.println("Here done");

		}

		public void dequeue(){

			if(isEmpty()){
				//System.out.println("Empty");
				return;
			}
			System.out.println(head.data);
			head = head.prev ;
		}

		public boolean isEmpty(){

			if(head == null)
				return(true);
			return(false);
		}

		/*public static void main(String[] args){

			Queue q = new Queue();
			q.enqueue("mark");
			q.enqueue("jan");
			q.dequeue();
			q.dequeue();
			q.dequeue();

		}*/

	}