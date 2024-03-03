package LinkedLists

import LinkedLists.Node

//Doubly Linked List, implemented without a size variable
class DoublyLinkedList<T> {

  private var head: Node<T>? = null
  private var tail: Node<T>? = null
  private var testLinkedList = LinkedList<T>()

  //Used in LinkedListQueues
  var first: Node<T>? = null
    get() = head

  fun isEmpty(): Boolean {
    return head == null
  }

  override fun toString(): String {
    if (isEmpty()) {
      return "Empty list"
    }
    return head?.toString() ?: ""
  }

  //Head end insertion
  fun push(value: T) {
    val newNode = Node(value)
    if (isEmpty()) {
        newNode.next = null
        head = newNode
        tail = newNode
        return
    }
    newNode.next = head
    head?.prev = newNode
    head = newNode
  }

  //Tail end insertion
  fun append(value: T) {
    //Initialize a Node which holds the given value, points its prev to current
    //tail
    val newNode = Node(value = value, prev = tail)
    //If the list is empty this will be the only node in the list, and it will
    //point to itself as its prev
    if (isEmpty()) {
      head = newNode
      tail = newNode
      return
    }

    //If the tail is not null, the newNode will be its 'next'
    tail?.next = newNode

    /**
    *Since the former tail is pointing to the newNode with 'next', and the
    *newNode is pointing to tail with its 'prev', we can update the tail
    *pointer to point to the newNode, as it is the new tail
    */
    tail = newNode
  }

  //Insertion in middle of list
  fun insertAt(value: T, index: Int) {

    //Initialize newNode at start of list, with 'next' pointing to current head
    var newNode = Node(value = value)

    //If the list is empty, then we simply set newNode as head and tail
    if (isEmpty()) {
      head = newNode
      tail = newNode
      return
    }

    var currentIndex = 0
    var prev = head
    var current = head
    var next = current!!.next

    //Fast Forward through the list until either current is the tail or the
    //index of current has reached the given index
    while (next != null && currentIndex < index) {
      prev = current
      current = current!!.next
      next = current!!.next
      currentIndex++
    }

    //After the while loop has finished, we know either that we are at the end
    //of the list or that we have reached the desired index
    if (next == null && currentIndex < index) {
        print("Index not in list")
        return
    } else {
        //If next does not equal null, then we are still in the list, and we
        //have reached the given index. This is guaranteed to be insertion
        //within the list since this function does not allow insertion after
        //tail
        //Update previous and next of newNode such that its outgoing pointers
        //position it in between current and whatever is before it
        newNode.prev = current!!.prev
        newNode.next = current
        //Now that newNode has had its pointers updated, its neighbors need to
        //now change one pointer each so that they are connected to newNode
        //instead of each other. prev.next currently points to current, and
        //current.previous currently points to prev. We will update so that each
        //of those two outgoing pointers now go to newNode
        prev!!.next = newNode
        current.prev = newNode
      }
  }

  fun node(index: Int): Node<T>? {
    // 1
    var currentNode = head
    var currentIndex = 0

    // 2
    while (currentNode != null && currentIndex < index) {
      currentNode = currentNode.next
      currentIndex++
    }

    if (currentNode != null && currentIndex == index) {
      return currentNode
    } else {
        print("No node at given index")
        return null
      }
  }

  //Remove a specific Node
  fun remove(node: Node<T>?): T? {

    //Initialize variables which contain given node's 'prev' and 'next'
    val prev = node?.prev
    val next = node?.next

    //If 'prev' is not null, simply point its next to the given node's next
    if (prev != null) {
      prev.next = next
    } else {
      //If 'prev' is null, then that means the given node was the head. in such
      //a case, update the head variable to point to head's 'next'
      head = next
    }

    //Update the 'next' of the previous node to point to the 'prev' of the given
    //node
    next?.prev = prev

    //If next is null, the given node is the tail. In such a case, update the
    //tail variable to point to the tail's 'prev'
    if (next == null) {
      tail = prev
    }

    //Remove all outgoing pointers from the given node, freeing it for the
    //garbage collector
    node?.prev = null
    node?.next = null

    //The garbage collector will clean the given node up after the function
    //exits, but until then its value is still accessible, so return it
    return node?.value
  }

  fun removeIndex(index: Int): T? {

    var node = node(index)

    return remove(node)
  }

  
}
