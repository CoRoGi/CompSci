package DSA.Queues

interface Queue<T> {

    fun peek(): T?

    fun enqueue(element: T): Boolean

    fun dequeue(): T?

    val count: Int
        get

    val isEmpty: Boolean
        get() = count == 0
}
