package ch05.lambda

fun main() {
    val list = listOf(1, 2, 3, 4)
    println(list.filter { it % 2 == 0 })

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.filter { it.age > 30 })
    /*
    Функция filter сможет удалить из коллекции ненужные элементы, но
    не сможет изменить их. Для преобразования элементов вам понадобится
    функция map. Функция map применяет заданную функцию к каждому элементу коллекции, объединяя результаты в новую коллекцию. Например, вот как
    можно преобразовать список чисел в список их квадратов:
     */
    println(list.map { it * it })
    println(people.map { it.name })
    // более лакончиный вариант
    println(people.map(Person::name))

    //Вызовы функций можно объединять в цепочки. Например, давайте выведем имена всех, кто старше 30:
    println(people.filter { it.age > 30 }.map(Person::name))
    /*
    Теперь допустим, что вам нужны имена самых взрослых людей в груп­ пе. Как это сделать? Можно найти максимальный возраст в группе и вернуть список всех с тем же возрастом. Такой код легко написать с помощью
    лямбда-выражений:
    people.filter { it.age == people.maxByOrNull(Person::age).age }
    Но обратите внимание : этот код повторяет процесс поиска максимального возраста для каждого человека - следовательно, если в коллекции
    хранится список из 100 человек, поиск максимального возраста будет выполнен 100 раз
    Следующее решение не имеет этого недостатка и рассчитывает максимальныи возраст только один раз
     */
    val maxAge = people.maxByOrNull(Person::age)?.age
    println(maxAge)
    people.filter { it.age == maxAge }

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.toUpperCase() })
    /*
    Обратите внимание: существуют отдельные функции для обработки
    ключей и значений. Функции fi lterKeys и mapKeys отбирают и преобразуют ключи словаря соответственно, тогда как filterValues и mapValues
    отбирают и преобразуют значения.
     */
    val canBeInClub27 = { p: Person -> p.age <= 27 }
    // для всех элементов
    println(people.all(canBeInClub27))
    // когда нужно найти хотя бы 1 подходящий элемент
    println(people.any(canBeInClub27))

    val list1 = listOf(1, 2, 3)
    println(!list.all { it == 3 })
    println(list.any { it != 3 })

    //Если требуется узнать, сколько элементов удовлетворяет предикату, ис- "" пользуите count:
    println(people.count(canBeInClub27))
    /*
    Выбор правильной функции: count или size
    Можно легко забыть о методе count и реализовать подсчет с помощью фильтрации коллекции и получения ее размера:
    >>> println(people.filter(canBeInClub27).size)
    Но в этом случае для хранения всех элементов, удовлетворяющих предикату, будет
    создана промежуточная коллекция. С другой стороны, метод count только подсчитывает
    количество подходящих элементов, а не сами элементы, поэтому он более эффективен.
    Всегда пытайтесь подобрать операцию, наиболее подходящую вашим потребностям.
     */
    // Чтобы найти элемент, удовлетворяющий предикату, используйте функцию find:
    println(people.find(canBeInClub27))

    /*
    Представьте, что вам нужно разделить элементы коллекции на разные
    группы по некоторому критерию - например, разбить список людей на
    группы по возрасту. Было бы удобно передать этот критерий непосредственно в качестве параметра. Функция groupBy может сделать это для вас:
     */
    val people1 = listOf(Person("Alice", 31), Person("Bob", 29), Person("Carol", 31))
    println(people1.groupBy { it.age })

    /*
    Каждая группа сохраняется в виде списка, поэтому результат будет
    иметь тип Map<Int, List<Person>>.

    В качестве другого примера посмотрим, как группировать строки по
    первому символу, используя ссылку на метод:
    */
    val list2 = listOf("a", "ab", "b")
    println(list2.groupBy(String::first))
    /*
    Обратите внимание, что функция ftrst является не членом класса
    String, а функцией-расширением. Тем не менее к ней можно обращаться
    через ссылку на метод.
     */

    /*
    Функция flatMap сначала преобразует (или отображает - тар) каждый
    элемент в коллекцию, согласно функции, переданной в аргументе, а затем
    собирает (или уплощает - flattens) несколько списков в один. Пример со
    строками хорошо иллюстрирует эту идею
     */

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })
    /*
    Если применить функцию toList к строке, она
    превратит её в список символов. Если использовать
    функцию map вместе с функцией toList, получится список списков символов
     */
    val books = listOf(Book("Thursday next", listOf("Jasper Fforde")), Book("Mort", listOf("Terry Pratchett")), Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman")))
    println(books.flatMap { it.authors }.toSet())
    /*
    Каждая книга может быть написана несколькими авторами, список которых хранится в свойстве book " authors. Функция flatMap объединяет авторов всех книг в один плоский список. Функция toSet удаляет дубликаты
    из получившейся коллекции: так, в этом примере Терри Пратчетт (Terry
    Pratchett) появится в выводе программы только один раз.
    Но если потребуется просто плоская коллекция, без преобразований, используйте функцию fl.atten : listOfLists . flatten( ).
     */



}

class Book(val title: String, val authors: List<String>)