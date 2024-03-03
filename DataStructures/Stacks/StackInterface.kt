package Stacks

interface StackInterface<T> {
    val storage: ArrayList<T>

    var size: Int

    fun peek(): T?

    fun push(item: T)

    fun pop(): T?
}
