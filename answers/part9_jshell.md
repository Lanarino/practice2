# Часть 9 — Эксперименты в jshell

## Как запустить jshell

Откройте терминал IntelliJ (View → Tool Windows → Terminal) и введите:
```
jshell
```
Для выхода: `/exit`

---

## Задание 9.1: Sealed-классы

### Команды (скопируйте и вставьте в jshell)

```
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}
Shape s = new Circle(5)
s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
```

### Фактический вывод:

```
jshell> sealed interface Shape permits Circle, Square {}
jshell> record Circle(double r) implements Shape {}
jshell> record Square(double side) implements Shape {}
jshell> Shape s = new Circle(5)
s ==> Circle[r=5.0]
jshell> s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
$17 ==> "Круг r=5.0"
```

### Вопрос: Что произойдёт при попытке создать `record Triangle(double a) implements Shape {}`?

**Ваш ответ:**
class is not allowed to extend sealed class: Shape (as it is not listed in its 'permits' clause)

Потому что интерфейс Shape — sealed, то есть «запечатанный». Следовательно разрешает реализовывать себя только Circle и Square.
А Triangle в этом списке нет — значит, нельзя. Java просто не даст скомпилировать такой код.


---

## Задание 9.2: Цепочка лямбд

### Команды

```
import java.util.function.*
Function<String, String> trim = String::trim
Function<String, String> upper = String::toUpperCase
Function<String, String> exclaim = s -> s + "!"
var pipeline1 = trim.andThen(upper).andThen(exclaim)
var pipeline2 = exclaim.compose(upper).compose(trim)
pipeline1.apply("  hello world  ")
pipeline2.apply("  hello world  ")
```

### Фактический вывод:

```
jshell> import java.util.function.*
   ...> 

jshell> Function<String, String> trim = String::trim
trim ==> $Lambda/0x000007ff01048c58@5010be6

jshell> Function<String, String> upper = String::toUpperCase
upper ==> $Lambda/0x000007ff010490b0@238e0d81

jshell> Function<String, String> exclaim = s -> s + "!"
exclaim ==> $Lambda/0x000007ff01049508@728938a9

jshell> var pipeline1 = trim.andThen(upper).andThen(exclaim)
pipeline1 ==> java.util.function.Function$$Lambda/0x000007ff01018628@6267c3bb
                                                    ^
jshell> var pipeline1 = trim.andThen(upper).andThen(exclaim)
   ...> 
pipeline1 ==> java.util.function.Function$$Lambda/0x000007ff01018628@533ddba

jshell> var pipeline2 = exclaim.compose(upper).compose(trim)
pipeline2 ==> java.util.function.Function$$Lambda/0x000007ff01018878@26a1ab54

jshell> pipeline1.apply("  hello world  ")
$11 ==> "HELLO WORLD!"

jshell> pipeline2.apply("  hello world  ")
   ...> 
$12 ==> "HELLO WORLD!"
```

### Вопрос: Дают ли `andThen()` и `compose()` одинаковый результат? В каком случае результаты будут различаться?

**Ваш ответ:**
Да, в этом примере andThen() и compose() дают одинаковый результат — обе функции возвращают "HELLO WORLD!".
Потому что:
pipeline1 = trim.andThen(upper).andThen(exclaim) означает: сначала trim, потом upper, потом exclaim.
pipeline2 = exclaim.compose(upper).compose(trim) означает: сначала trim, потом upper, потом exclaim — то же самое!
Просто andThen строит цепочку в прямом порядке, а compose — в обратном, но так как мы композируем их справа налево (exclaim.compose(upper).compose(trim)), то итоговый порядок выполнения совпадает.
Результаты будут различаться, если:
Поменять порядок функций,
Или использовать andThen и compose по-разному.


---

## Задание 9.3: Сравнение EnumSet и HashSet

### Команды

```
enum Color { RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE, BLACK }
var enumSet = java.util.EnumSet.of(Color.RED, Color.GREEN, Color.BLUE)
var hashSet = new java.util.HashSet<>(java.util.Set.of(Color.RED, Color.GREEN, Color.BLUE))
enumSet.contains(Color.RED)
hashSet.contains(Color.RED)
enumSet.getClass().getSimpleName()
hashSet.getClass().getSimpleName()

```

### Фактический вывод:

```
jshell> enum Color { RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE, BLACK }
   ...> 
|  created enum Color
jshell> var enumSet = java.util.EnumSet.of(Color.RED, Color.GREEN, Color.BLUE)
   ...> 
enumSet ==> [RED, GREEN, BLUE]
jshell> var hashSet = new java.util.HashSet<>(java.util.Set.of(Color.RED, Color.GREEN, Color.BLUE))
   ...> 
hashSet ==> [RED, GREEN, BLUE]
jshell> enumSet.contains(Color.RED)
   ...> 
$21 ==> true
jshell> hashSet.contains(Color.RED)
   ...> 
$22 ==> true
jshell> enumSet.getClass().getSimpleName()
$23 ==> "RegularEnumSet"
jshell> hashSet.getClass().getSimpleName()
   ...> 
$24 ==> "HashSet"

```

### Вопрос: Почему внутренний класс EnumSet называется `RegularEnumSet`? Что произойдёт, если enum будет иметь больше 64 констант?

**Ваш ответ:**
EnumSet — это специальная коллекция для перечислений, и она работает очень быстро, потому что внутри использует битовые маски (т.е. каждый элемент enum представляется одним битом в числе).
Когда в enum 64 или меньше констант, Java использует оптимизированную реализацию под названием RegularEnumSet — она хранит всё в одном значении типа long (а в long как раз 64 бита, хватает по одному на каждый элемент).
Если же в enum будет больше 64 констант, то один long уже не вместит все биты, и тогда Java автоматически переключится на другую реализацию — JumboEnumSet, которая использует массив из нескольких long-ов.
То есть название RegularEnumSet просто означает: «обычный, небольшой enum — всё помещается в один long».
