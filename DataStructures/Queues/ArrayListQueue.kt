package Queues

import Queues.Queue

class ArrayListQueue<T>: Queue<T> {

  var storage = arrayListOf<T>()

  override val count : Int
    get() = storage.size

  override fun peek(): T? {
    if (isEmpty) return null

    else {
        return storage[0]
    }
  }

  override fun enqueue(element: T): Boolean {
    storage.add(element)
    return true
  }

  override fun dequeue(): T? {
    if (isEmpty) return null
    else return storage.removeAt(0)
  }
    
}
