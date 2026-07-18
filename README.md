# JustARod Forge 1.20.1 非官方降级分支

本仓库是 [CSneko/JustARod](https://github.com/CSneko/JustARod) 的非官方 Minecraft 1.20.1 Forge 降级分支，基于上游 `0.2.2` 源码继续开发，并非原作者发布的官方版本。

项目保留上游作者署名并继续使用 GPL-3.0 许可证开源。Forge 降级产生的问题请反馈到本仓库，不要打扰上游作者。

## 当前目标

- Minecraft 1.20.1
- Forge 47.4.21
- Java 17
- toNeko Forge 1.9.0 前置
- GeckoLib Forge 4.4.9 前置
- Forgified Fabric API 1.20.1 前置，用于承接尚未完全改写的 Fabric 回调

源码采用 `common + forge` 双模块：common 保留已完成的 1.20.1 Yarn 源码降级，Forge 模块负责生产映射、原生 `@Mod` 入口、Mixin/Access Transformer 和发布打包。最终产物是 Forge JAR，不需要 Fabric Loader 或 Fabric Language Kotlin；Kotlin 运行库与 Energy API 已包含在模组内。

## 构建

先构建同级目录的 toNeko Fabric/Forge 1.20.1 工程，再执行：

```powershell
.\gradlew.bat :forge:build
```

发布文件：

`forge/build/libs/justarod-forge-0.2.2+1.20.1-forge.0.jar`

不要把 `-sources.jar` 或 `-dev-shadow.jar` 放进游戏。

## 安装与测试

安装文件和人工测试项目见 [TESTING_CHECKLIST_CN.md](TESTING_CHECKLIST_CN.md)。移植状态和技术说明见 [FORGE_BACKPORT.md](FORGE_BACKPORT.md)。

