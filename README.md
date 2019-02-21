# Java Auto catch

Simplify *try catch* block

### Maven
```xml
<dependency>
    <groupId>com.github.vgalloy</groupId>
    <artifactId>auto-catch</artifactId>
    <version>1.1.1</version>
</dependency>
```

### Usage

*Callable wrapping*:
```java
try {
    Thread.sleep(1_000);
} catch (final InterruptedException e) {
    throw new RuntimeException(e);
}
```

With Auto catch :
```java
AutoCatch.autoCatch(() -> Thread.sleep(1_000));
```

*Supplier wrapping*:
```java
final File file = new File("test");
final String canonicalName;
try {
    canonicalName = file.getCanonicalPath();
} catch (final IOException e) {
    throw new RuntimeException(e);
}
```

With Auto catch :
```java
final File file = new File("test");
final String canonicalName = AutoCatch.autoCatch(file::getCanonicalPath);
```


