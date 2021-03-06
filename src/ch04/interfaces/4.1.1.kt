package ch04.interfaces

/*
Вместо ключевых слов extends и implements, используемых в Java, в
языке Kotlin используется двоеточие после имени класса. Как в Java, класс
может реализовать столько интерфейсов, сколько потребуется, но наследовать только один класс.
 */

fun main() {
    Button().click()
    Button().showOff()
    Button().setFocus(true)
    /*
    Версия Kotlin 1.0 разрабатывалась с прицелом на совместимость с Java 6, которая не
    поддерживает реализацию методов по умолчанию в интерфейсах. Поэтому каждый интерфейс с методами по умолчанию компилируется как сочетание обычного интерфейса
    и класса с реализацией в виде статического метода. Интерфейс содержит только объявления, а класс - все реализациями методов. Поэтому, если понадобится реализовать
    такой интерфейс в Jаvа-классе, вам придется добавить собственные реализации всех
    методов, в том числе тех, для которых в Kotlin определено тело метода.
     */

    /*
    Для решения этой проблемы Joshua Bloch (Джошуа Блох) в своей книге
    <<Effective Java>> (Addison-Wesley, 2008)1, одной из самых известных книг о
    стиле программирования на Java, рекомендует <<проектировать и документировать наследование или запрещать его>>. Это означает, что все классы
    и методы, которые не предназначены для переопределения в подклассах,
    должны снабжаться модификатором final.
    Kotlin тоже придерживается этой философии. В Java все классы и методы
    открыты по умолчанию, а в Kotlin они по умолчанию отмечены модификатором final.
    Если вы хотите разрешить наследовать свой класс, объявите его открытым, добавив модификатор open.
    Вам также понадобится добавить модификатор open ко всем свойствам и методам, которые могут быть переопределены.

     */

    /*
    Открытые классы и умное приведение типов
    Одно из основных преимуществ классов, закрытых по умолчанию: они позволяют выполнять автоматическое приведение типов в большем количестве сценариев. Как уже
    упоминалось в разделе 2.3.5, автоматическое приведение возможно только для переменных, которые нельзя изменить после проверки типа. Для класса это означает,
    что " автоматическое приведение типа может применяться только к неизменяемым своиствам
    с модификатором val со стандартным методом доступа. Из этого следует, что свойство
    должно быть final - иначе подкласс сможет переопределить свойство и определить
    собственный метод доступа, нарушая основное требование автоматического приведения типов. Поскольку по умолчанию свойства получают модификатор final,
    автоматическое приведение работает с большинством свойств в вашем коде, что повышает его выразительность.
     */
}

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickalble")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")

    fun showOff() = println("I'm focusable")
}

class Button: Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

open class RichButton : Clickable {
    fun disable(){}

    open fun animate() {}

    // переопределенный метод всегда открыт по умолчанию
   final override fun click() {}
}

/*
Как в Java, в Kotlin класс можно объявить абстрактным, добавив ключевое слово abstract, и создать экземпляр такого класса будет невозможно.
Абстрактный класс обычно содержит абстрактные методы без реализации,
которые должны быть переопределены в подклассах. Абстрактные методы всегда открыты, поэтому не требуется явно использовать модификатор
open. Вот пример такого класса.
 */

/*
final - не может быть переопределен, применяется к членам класса по умолчанию
open - может быть переопределен, должен указываться явно
abstract - должен быть переопределен, используется только в абстрактных классах абстрактные методы не могут иметь реализацию
override - переопределяет метод суперкласса или интерфейса, по умолчанию переопределяющий метод открыт, если только не объявлен как final
 */

// Нельзя создать экземпляр абстрактного класса
abstract class Animated {
    abstract fun animate()

    open fun stopAnimating() {

    }

    fun animatedTwice() {

    }
}