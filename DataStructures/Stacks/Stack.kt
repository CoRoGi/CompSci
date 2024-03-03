package Stacks

import Stacks.StackInterface

class Stack<T> : StackInterface<T> {
    override val storage = ArrayList<T>()

    override var size = 0

    override fun peek(): T? {
        if (storage.isEmpty()) {
            return null
        } else {
            return storage[size - 1]
        }
    }

    override fun push(item: T) {
        storage.add(item)
        size++
    }

    override fun pop(): T? {
        if (storage.isEmpty()) {
            return null
        } else {
            val poppedItem = storage.removeLastOrNull()
            size--
            return poppedItem
        }
    }
}
