package LinkedLists

import LinkedLists.Node

class LinkedList<T>() {

  var head: Node<T>? = null
  var tail: Node<T>? = null
  var size = 0

  fun isEmpty(): Boolean {
      if (size == 0) {
          return true
      }
      return false
    }

  override fun toString(): String {
    if (isEmpty()) {
      return "List is Empty"
    } else {
      return head!!.toString()
    }
  }

  //Head-end insertion. Adds a node containing 'value' as the new head
  fun push(value: T) {
    //Update the value of head. The previous head is this node's 'next'
    head = Node(value, head)
    //If the tail is null, the list was initially empty, so the pushed node
    //will be both the head and the tail
    if (tail == null) {
      tail = head
    }
    //Increment the size. Very Important for indexing
    size++
  }

  //Tail-end insertion. Adds a node containing 'value' as the new tail
  fun append(value: T) {
    //If the list is empty, tail end insertion will be the same as head end
    //insertion. Therefore, we will use push since the logic is already
    //implemented there for pushing to an empty list
    if (isEmpty()) {
      push(value)
      return
    }
    //If the list is not empty, then tail is not null. Since tail is not null,
    //we will update the value of its 'next' to be the node we are appending
    tail!!.next = Node(value, null)
    //Since the node we are appending will be the new tail, we update the value
    //of tail to be the former tail's 'next', which is the newly appended node
    tail = tail!!.next
    //Incrementing the size
    size++
  }

  //The search method for LinkedLists. Since there is no indexing, linear search
  //is the only option for a LinkedList
  fun nodeAt(index: Int): Node<T>? {
    //Initializing the iterators for the LinkedList
    var currentNode = head
    var currentIndex = 0

    //This is essentially a fast-forward through the LinkedList. If currentNode
    //becomes null, we are past the end of the LinkedList. if currentIndex is
    //equal to or greater than index, then we have overshot 
    while (currentNode != null && currentIndex < index) {
      currentNode = currentNode.next
      currentIndex++
    }
    //After the while loop has finished, either we have reached the end of the
    //LinkedList (tail.next, which is null), or we are at the index we want.
    //Therefore, we check if currentIndex is equal to the given index (meaning
    //we've reached the desired Node) and also check to make sure this node is
    //not null (we could be at tail.next, which is null). If both are true, we
    //will return a node with a real value. Else we will return null.
    if (currentIndex == index && currentNode != null) {
      return currentNode
    } else {
      print("No node at index $index")
      return null
    }
  }

  //Insertion in the middle of the list.
  fun insert(value: T, afterIndex: Int? = null) {
    if (isEmpty()) {
      //If the list is empty, then we will simply push this value
      print("List is empty, inserting as head")
      push(value)
    } else if (afterIndex == null) {
      //If the list is not empty, but no index is given for where to insert,
      //then we will insert at the head
      print("No afterIndex given, inserting at head")
      push(value)
    } else if (afterIndex >= size || afterIndex < 0) {
      //If the list is not empty and the user gives an input, but that input is
      //not within the bounds of the list (as maintained by the size variable),
      //then do nothing
      print("Index not in list")
    } else if (afterIndex == size - 1) {
      //If the list is not empty and the user gives a valid index, but that
      //index corresponds to the tail, then we can just append that value
      append(value)
    } else {
      //After the above checks, we can guarantee that the node will be inserted
      //in the middle of the list.
      //Create a node containing the given value
      var newNode = Node(value)
      //This new node will have as its 'next' whatever the 'next' is of the node
      //which it will be inserted after
      newNode.next = nodeAt(afterIndex)!!.next
      //The 'next' of the node which newNode was inserted after should now be
      //set to newNode. With this the sequence will be nodeAt(afterIndex) ->
      //newNode -> newNode.next (formerly nodeAt(afterIndex).next)
      nodeAt(afterIndex)!!.next = newNode
      //Increment the size
      size++
    }
  }

  //Gets the value of whatever node was the head of the list and removes it from
  //the list
  fun pop(): T? {
    //If the list is empty, there is no value to get from it
    if (isEmpty()) return null
    //Since the list is not empty, head is not null and so has a non-null value,
    //which is what we will store to return from this method
    var value = head!!.value
    //Before returning, we must remove the head from the list by making the next
    //node the head
    head = head!!.next
    //Since we have updated the value of head, there are no longer any
    //references to the previous head, meaning it is not a part of the list
    //anymore. Therefore, size must be decremented
    size--
    //Now that the bookkeeping is done, we can return the value we saved from
    //the former head
    return value
  }

  //Get the value of whatever node is the tail of the list and removes that node
  //from the list. To reach the end of the list, we will need to iterate through
  //it. This is because to remove the tail, we have to remove the reference to
  //it as the 'next' of the node before it. The only way to reach that node is
  //through a linear iteration
  fun removeLast(): T? {
    //Start at the head of the list. If head is null, then the list is empty and
    //there is only a null value to be returned from the list.
    val head = head ?: return null
    //If there is no value after head, then head is the first and last node in
    //this list, so we can pop it
    if (head.next == null) return pop()
    //Decrement the size of the list
    size--
    //Initialize 3 variables to keep track of our position in the list as we
    //iterate through it
    var prev = head
    var current = head
    var next = current.next
    //As long as next is not equal to null, we are not at the end of the list.
    //Only the tail of the list has a next which is equal to null. Therefore, we
    //can iterate through the list by using this while loop until current is the
    //tail (i.e. until current.next is equal to null)
    while (next != null) {
      prev = current
      current = next
      next = current.next
    }
    //Once this loop is done, current is guaranteed to be the tail of the list.
    //Since we have kept a reference to whatever the node before current is
    //while iterating. prev is guaranteed to be the node before the tail.
    //Therefore, by setting prev.next to null, there are no references to the
    //tail from inside of the list. The only persistent reference
    //remaining to the node currently at the tail is the tail variable. The
    //current variable also contains a reference to that Node, but it will be
    //destroyed once the function completes
    prev.next = null
    //Since tail is the last remaining reference to the Node being removed from
    //the list, simply updating the value of tail to be prev frees that Node to
    //be destroyed by the garbage collector after this function finishes
    tail = prev
    //Before this function finishes, the removed Node's value needs to be
    //returned. Since current still holds a reference to the former tail, we can
    //return its value before ending the function, and when the function ends
    //current's scope will end, removing the last reference to the former tail
    return current.value
  }

  //Removal after a specfic index in the list
  fun removeAfter(index: Int): T? {
    //If the Node at whatever index we want to delete the 'next' of is null,
    //then its 'next' will obviously also be null, so we can return null
    if (nodeAt(index) == null) {
        print("No node after index")
        return null
    }
    //Since nodeAt(index) is confirmed to not be null at this point, save it
    val node = nodeAt(index)
    //Store the value of whatever node is after nodeAt(index)
    val result = node!!.next?.value
    //If the node after nodeAt(index) is the tail, then update the tail variable
    //to be the nodeAt(index), since the former tail is what will be removed
    if (node.next == tail) {
        tail = node
    }
    //If the node after nodeAt(index) were null, then nodeAt(index) is the tail,
    //and we would therefore not be removing anything from the list since the
    //node after the tail is null by definition. We would not want to decrement 
    //the size in that case, so we check to make sure the node after
    //nodeAt(index) is not null before decrementing the size
    if (node.next != null) {
        size--
    }
    //Since the tail variable has already been updated, the only remaining
    //reference to the node being removed is the 'next' of nodeAt(index). By
    //simply updating the value of nodeAt(index)'s 'next' to be whatever the
    //'next' is of the node being removed, the list remains intact while the
    //node being removed is no longer referenced and can be garbage collected
    node.next = node.next?.next

    //With the bookkeeping done, we can finally return the result we stored
    //earlier
    return result
  }

}
