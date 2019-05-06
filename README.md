# Lazy

If you are programming in Scala you can write

```scala
lazy val z = "Hello"
```

and the expression will only be evaluated when `z` is accessed the first
time.

If you progam in Kotlin you can write something like

```kotlin
val z: String by lazy { "Hello" }
```

and the expression will only be evaluated when `z` is accessed the first
time.

What can you write in Java?

Using

```xml
    <groupId>com.javax0</groupId>
    <artifactId>lazylet</artifactId>
    <version>1.0.0</version>
```

you can write

```java
var z = Lazy.let("Hello");
```

This is not part of the Java language but it is an extremely simple
class. The use is simple as demonstrated in the unit tests:

```java
    private static class TestSupport {
        int count = 0;

        boolean callMe() {
            count++;
            return true;
        }
    }

    ...
    
final var ts = new TestSupport();
var z = Lazy.let(ts::callMe);
if (false && z.get()) {
    Assertions.fail();
}
Assertions.assertEquals(0, ts.count);
z.get();
Assertions.assertEquals(1, ts.count);
z.get();
Assertions.assertEquals(1, ts.count);
```