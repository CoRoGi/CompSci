package LinkedLists

import LinkedLists.Node

class LinkedList<T: Any>() {

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

  fun push(value: T) {
    head = Node(value, head)
      if (tail == null) {
        tail = head
      }
    size++
  }

  fun append(value: T) {
    if (isEmpty()) {
      push(value)
        return
    }
    tail!!.next = Node(value, null)
    tail = tail!!.next
    size++
  }

  fun nodeAt(index: Int): Node<T>? {
    var currentNode = head
    var currentIndex = 0

      while (currentNode != null && currentIndex < index) {
        currentNode = currentNode.next
        currentIndex++
      }
    if (currentIndex == index) {
      return currentNode
    } else {
      print("No node at index $index")
      return null
    }
  }

  fun insert(value: T, afterIndex: Int? = null) {
    var newNode = Node(value)
    if (isEmpty()) {
      print("List is empty, inserting as head")
      push(value)
    } else if (afterIndex == null) {
      print("No afterIndex given, inserting at head")
      push(value)
    } else if (afterIndex >= size || afterIndex < 0) {
      print("Index not in list")
    } else if (afterIndex == size - 1) {
      append(value)
    } else {
      newNode.next = nodeAt(afterIndex)!!.next
      nodeAt(afterIndex)!!.next = newNode
      size++
    }
  }

  fun pop(): T? {
    if (isEmpty()) return null
    var value = head!!.value
    head = head!!.next
    size--
    return value
  }

  fun removeLast(): T? {
    val head = head ?: return null
    if (head.next == null) return pop()
    size--
    var prev = head
    var current = head
    var next = current.next
    while (next != null) {
      prev = current
      current = next
      next = current.next
    }
    prev.next = null
    tail = prev
    return current.value
  }

  fun removeAfter(index: Int): T? {
    if (nodeAt(index) == null) {
        print("No node after index")
        return null
    }
    val node = nodeAt(index)
    val result = node!!.next?.value
    if (node.next == tail) {
        tail = node
    }
    if (node.next != null) {
        size--
    }
    node.next = node.next?.next

    return result
  }

}
