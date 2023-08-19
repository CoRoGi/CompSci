package DSA.Stacks

interface StackInterface<T: Any> {

    val storage: ArrayList<T>

    val size: Int

    fun peek(): T?

    fun push(item: T)

    fun pop(): T?

  }
