package Queues

import Stacks.Stack 

class StackQueue<T>: Queue<T> {

  private var leftStack = Stack<T>()

  private var rightStack = Stack<T>()

  override val count: Int
    get() = leftStack.size + rightStack.size

  private fun transferElements() {
    while (rightStack.size > 0) {
      leftStack.push(rightStack.pop()!!)
    }
  }

  override fun peek(): T? {
    if (rightStack.size > 0) {
      transferElements()
    }
    return leftStack.peek()
  }

  override fun enqueue(element: T): Boolean {
      rightStack.push(element)
      return true
  }

  override fun dequeue(): T? {
    if (rightStack.size > 0) {
      transferElements()
    }
    return leftStack.pop()
  }

}
