package LinkedLists

data class Node<T>(
  var value: T,
  var next: Node<T>? = null,
  var prev: Node<T>? = null,
) {
  override fun toString(): String {
    return if (next != null) {
      "$value -> ${next.toString()}"
    } else {
      "$value"
    }
  }

  fun printInReverse() {
    next?.printInReverse()

      if (next != null) {
        print(" -> ")
      }

    print(value.toString())
  }
}
