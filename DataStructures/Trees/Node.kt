package Trees

import Queues.LinkedListQueue

typealias Visitor<T> = (Node<T>) -> Unit

class Node<T>(val value: T, var parent: Node<T>? = null) {
    private val children = mutableListOf<Node<T>>()

    fun add(child: Node<T>) {
        children.add(child)
    }

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)

        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    fun forEachLevelOrder(visit: Visitor<T>) {
        visit(this)

        val queue = LinkedListQueue<Node<T>>()

        children.forEach {
            queue.enqueue(it)
        }

        var child = queue.dequeue()

        while (child != null) {
            visit(child)
            child.children.forEach{
                queue.enqueue(it)
            }
            child = queue.dequeue()
        }
    }

    fun search(value: T): Node<T>? {
        var result: Node<T>? = null
        forEachLevelOrder {
            if (it.value == value) {
                result = it
            }
        }
        return result
    }
}
