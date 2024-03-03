package Queues

import LinkedLists.LinkedList

class LinkedListQueue<T> : Queue<T> {

    private val storage = LinkedList<T>()

    private var size = 0

    override val count
        get() = size

    override fun enqueue(element: T): Boolean {
        storage.append(element)
        return true
    }

    override fun dequeue(): T? {
        return storage.pop()
    }

    override fun peek(): T? {
        return if (isEmpty) {
            null
        } else {
            storage.head!!.value
        }
    }
}
