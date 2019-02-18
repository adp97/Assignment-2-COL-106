Assignment 2 COL 106
Each employee(EmpNode) in Company class(company heirarchy) is made of 4 things:
  1) Name of employee
  2) level of employee
  3) immediate parent(boss) of employee 
  4) immedidate children(juniors) of the said employee.
  The juniors are in a singly linked list created using the LinkedNode program for each employee during initialization.
  
The Company class is supported with two other classes namely:
  1) SearchTree program that is dictionary implemented using BST for searching employee names.
  2) Queue program that queues all the employees for the purpose of printing as well as for searching a given employee when found in the dictionary.
  3) LinkedNode program is primarily meant to keep track of the immediate juniors of each employee in the Company heirarchy. 
  
Company.java program does the following things 
  1) AddEmployee(S,S')- Adding a new employee to an existing heirarachy of employees where it's boss and level is assigned as         requested. It will first search for the employee S' using EmpSearch(Empnode n) to see if S' exists. If found it will assign S as its child, make S' parent of S and update S's level. If not found it throws a AdditionError. Simultaneously, the BST was kept updated using its insert(S) function.
  
  2) DeleteEmployee(S,S')- To delete S from the company heirarchy and assign its children(juniors) to S'. The two employees are first search using search(S) function of the BST. If not found it raises DeletionError. Next we check if their levels are same. Again we raise a DeletionError if that is not the case. If all goes well, it deletes employee node with name S and assign its juniors to S'. Also, it is ensured that employee nodes that don't have any children are dealt with.Simultaneously, the BST was kept updated using the delete(S) function.
  
  3) LowestCommonBoss(S,S')- To find the lowest common boss we first figure out the difference in levels of the two employees in question. Then we match the difference to 0 if it wasn't already the case. Finally we check to see where the employees' parent nodes match using the parent value associated with them going all the way up to the root(CEO). Finally we print the most immediate boss when found. 
  
  4) printCompanyTree() - Here we make use of the Queue subclass. We do a breath first traversal of the Company heirarchy. As we visit each node we enqueue them to a queue(instance of Queue class). Then we add its children too using enqueueChildren function in the LinkedNode class. Finally we dequeue(print) the head of queue. Employees with no children were taken care of so they were only dequeued when head was pointing at them.
  
  Apart from these function there were other functions defined in the various programs as helper functions to the above. 
  
  The main() function of Company.java takes in a .txt file consisting of employees as argument and first creates a Company heirarchy using the initial list. 
  Then as requested it performs the above mentioned functions reading the Query number mentioned in the text. 
  
  
     
