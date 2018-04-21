# Calculator

一个用JavaFX写的简易版计算器。

## Required

* Java 8 或更高, 支持 JavaFX.

* Ant 1.7.1 或更高, 支持 Sonar Scanner for Ant.

## Ant Command

Build.

```sh
$ ant

# or
$ ant build
```

Build Jar file.

```sh
$ ant release
```

Build and run.

```sh
$ ant run
```

Build and run Jar file.

```sh
$ ant runjar
```

JUnit.

```sh
$ ant test
```

Sonar scanner, 需要提前开启Sonar Qube平台.

Sonar可能需要重复开启方可进入`localhost:9000`, 才能开始scan.

```
$ ant sonar
```

Clean.

```sh
$ ant clean
```

## File Tree

```
build/                    # 构建文件夹, 存储class文件
dist/                     # 生成目标文件夹, 存储jar文件
lib/                      # 库文件夹, 存储库
src/                      # 源码文件夹, 存储main和test源码
build.xml                 # Ant项目文件
sonar-project.properties  # Sonar配置文件, 在Ant < 1.7.1时可用
```
