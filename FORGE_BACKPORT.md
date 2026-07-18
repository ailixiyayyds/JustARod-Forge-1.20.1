# JustARod Forge 1.20.1 移植状态

## 项目关系

这是 [CSneko/JustARod](https://github.com/CSneko/JustARod) 的独立、非官方、公开源码 Forge 1.20.1 降级分支。上游基线为 JustARod 0.2.2；本分支保留 GPL-3.0 许可证和原作者署名。

## 已完成

- 将 Minecraft 1.21 源码降级到 Minecraft 1.20.1 / Java 17。
- 物品、方块、实体、状态效果、属性、指令、数据同步、客户端界面、GeckoLib 渲染器和 Mixins 均已通过 1.20.1 编译。
- 建立 `common + forge` 构建结构，将 Yarn common 产物转换为 Forge 生产映射。
- 添加原生 Forge `@Mod("justarod")` 入口，同时启动服务端与客户端初始化逻辑。
- 将 Fabric Access Widener 对应权限转换为 Forge Access Transformer。
- 将 Kotlin 标准库、协程和 TeamReborn Energy API 打进发布 JAR，玩家不需要另装 Fabric Language Kotlin 或 Energy API。
- 声明 toNeko、Forgified Fabric API、GeckoLib 的 Forge 运行时依赖。
- `:forge:build` 已成功，发布 JAR 的 `mods.toml`、Mixin refmap、入口类、Kotlin 与 Energy 类均已做静态检查。

## 尚待人工验证

按用户要求，本轮不启动 Minecraft 做运行测试。需要由测试人员验证注册时序、客户端渲染、网络包、所有交互与多人同步。若崩溃，请提交完整的 `latest.log` 和 `crash-reports` 文件，不要只提交截图。

兼容联动（Cuffed、Erotic Dungeon Game、Touhou Little Maid、NeedsOfNature）不属于本次“先完成两个 Forge 基础移植”的发布门槛，将在基础版本稳定后单独实现。

