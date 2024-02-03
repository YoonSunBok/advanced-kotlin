package generic

/**
 * 애플리케이션의 시작점입니다.
 * 참새와 독수리가 있는 케이지를 생성하고, 동물들을 정렬한 후 출력합니다.
 */
fun main() {
    val cage = Cage5(mutableListOf(Sparrow(), Eagle()))
    cage.printAfterSorting()
}

/**
 * Bird를 나타내는 추상 클래스입니다.
 * @property name Bird의 이름입니다.
 * @property size Bird의 크기입니다.
 * Animal과 Comparable 인터페이스를 구현합니다.
 */
abstract class Bird(
    name: String = "새",
    val size: Int = 100,
) : Animal(name), Comparable<Bird> {
    /**
     * 이 Bird와 지정된 Bird를 비교하여 순서를 반환합니다.
     * 이 Bird가 지정된 Bird보다 작으면 음의 정수, 같으면 0, 크면 양의 정수를 반환합니다.
     */
    override fun compareTo(other: Bird): Int {
        return this.size.compareTo(other.size)
    }
}

/**
 * Sparrow를 나타내는 클래스입니다.
 * Bird 클래스를 상속합니다.
 */
class Sparrow : Bird("참새", 100)

/**
 * Eagle을 나타내는 클래스입니다.
 * Bird 클래스를 상속합니다.
 */
class Eagle : Bird("독수리", 500)

/**
 * Cage를 나타내는 클래스입니다.
 * @property animals 케이지에 있는 동물들의 목록입니다.
 * 동물의 타입 T는 Animal과 Comparable 인터페이스를 모두 구현해야 합니다.
 */
class Cage5<T>(
    private val animals: MutableList<T> = mutableListOf(),
) where T : Animal, T : Comparable<T> {
    /**
     * 동물들을 정렬한 후 출력합니다.
     */
    fun printAfterSorting() {
        this.animals.sorted()
            .map { it.name }
            .let { println(it) }
    }

    /**
     * 케이지에서 첫 번째 동물을 반환합니다.
     */
    fun getFirst(): T {
        return animals.first()
    }

    /**
     * 동물을 케이지에 추가합니다.
     * @param animal 추가할 동물입니다.
     */
    fun put(animal: T) {
        this.animals.add(animal)
    }

    /**
     * 다른 케이지의 모든 동물을 이 케이지에 추가합니다.
     * @param otherCage 다른 케이지입니다.
     */
    fun moveFrom(otherCage: Cage5<T>) {
        this.animals.addAll(otherCage.animals)
    }

    /**
     * 이 케이지의 모든 동물을 다른 케이지로 이동합니다.
     * @param otherCage 다른 케이지입니다.
     */
    fun moveTo(otherCage: Cage5<T>) {
        otherCage.animals.addAll(this.animals)
    }
}

/**
 * List의 확장 함수입니다.
 * 이 리스트가 다른 리스트와 교집합이 있는지 확인합니다.
 * @param other 다른 리스트입니다.
 * @return 교집합이 있으면 true, 없으면 false를 반환합니다.
 */
fun <T> List<T>.hasIntersection(other: List<T>): Boolean {
    return (this.toSet() intersect other.toSet()).isNotEmpty()
}
