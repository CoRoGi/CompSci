import LinkedLists.*
import Queues.*
import Stacks.*
import Trees.*
import kotlin.random.Random

fun main() {
  println("This is the main Runner class for testing the Data Structures")
}

fun createLinkedList(size: Int): LinkedList<Int> {
    var linkedList = LinkedList<Int>()
    if (size > 100) {
        println("Pick a smaller number")
        return linkedList
    }
    while (size > 0) {
        linkedList.append(generateRandomNumber())
    }
    return linkedList
}

fun generateRandomNumber(): Int {
    var randomNumber = Random.Default.nextInt(0, 255)
    return randomNumber
}

fun createStack(size: Int): Stack<Int> {
    var stack = Stack<Int>()
    if (size > 100) {
        println("Pick a smaller number")
        return stack
    }
    while (size > 0) {
        stack.push(generateRandomNumber())
    }
    return stack
}

fun createQueue(size: Int): Queue<Int> {
    var queue = LinkedListQueue<Int>()
    if (size > 100) {
        println("Pick a smaller number")
        return queue
    }
    while (size > 0) {
        queue.enqueue(generateRandomNumber())
    }
    return queue
}
