# string-matching

字符串模式匹配算法的 Java 实现 + Swing 可视化演示。

## 算法

| 方法 | 算法 | 说明 |
| --- | --- | --- |
| `Algorithm.bf(main, sub)` | **BF**（Brute Force，暴力匹配） | 逐位比较，失配时主串指针回退 |
| `Algorithm.bm(main, sub)` | **BM**（Boyer-Moore） | 坏字符规则，从右向左比较 |

两者都接收 `char[]`，字符集按 `SIZE = 65536` 处理（**支持中文**）；返回首次匹配的起始下标，未匹配返回 `-1`。

## 运行演示

从 `App` 启动一个 Swing 窗口（`Frame` + `Panel`），在界面里输入主串与子串，直观看到两种算法的匹配过程。

## 工程结构

```
src/
├── Algorithm.java   # 两种匹配算法
├── App.java         # 入口
├── Frame.java       # 窗口
└── Panel.java       # 绘图 / 动画面板
lib/                 # commons-io、PGS Look and Feel（第三方 jar）
res/                 # 图标
```

## 说明

这是 2023 年的 Eclipse 工程：依赖以 `lib/` 下的 jar 形式提供、**没有构建文件**（无 `pom.xml` / `build.gradle`），所以克隆后需要手动把 `lib/*.jar` 加入 classpath 才能编译运行。
