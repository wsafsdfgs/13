# 企业规章制度问答机器人

一个基于Spring Boot和Vue3开发的企业规章制度智能问答系统，集成了阿里云大模型API和RAG（检索增强生成）技术，能够智能回答员工关于考勤制度、请假流程和报销规则等问题。

## 🌟 项目概述

本系统旨在提高企业内部规章制度的查询效率，减轻HR部门的工作负担，同时为员工提供便捷、准确的规章制度咨询服务。系统采用前后端分离架构，后端基于Spring Boot实现，前端使用Vue3开发，通过SSE实现实时对话体验。

## 🏗️ 系统架构

### 后端技术栈
- **Spring Boot 3.5.14** - 后端框架
- **Java 21** - 开发语言
- **LangChain4j** - 大语言模型应用框架
- **阿里云DashScope API** - 大模型服务
- **RAG技术** - 检索增强生成，提高回答准确性
- **Maven** - 项目构建和依赖管理

### 前端技术栈
- **Vue 3** - 前端框架
- **Vite** - 构建工具
- **Axios** - HTTP请求库
- **SSE (Server-Sent Events)** - 实时通信
- **Marked** - Markdown解析和渲染
- **CSS3** - 样式和动画

## 📁 项目结构

```
企业规章制度问答机器人/
├── ai-code-helper-frontend/          # 前端项目
│   ├── public/                       # 静态资源
│   ├── src/                          # 源代码
│   │   ├── api/                      # API接口
│   │   │   └── chatApi.js            # 聊天接口和快捷查询接口
│   │   ├── components/               # Vue组件
│   │   │   ├── ChatMessage.vue      # 聊天消息组件
│   │   │   ├── ChatInput.vue        # 输入框组件
│   │   │   └── LoadingDots.vue      # 加载动画组件
│   │   ├── utils/                    # 工具函数
│   │   │   └── index.js              # 通用工具
│   │   ├── App.vue                   # 主应用组件
│   │   └── main.js                   # 应用入口
│   ├── index.html                    # HTML模板
│   ├── vite.config.js                # Vite配置
│   ├── package.json                  # 依赖管理
│   └── README.md                     # 前端项目说明
├── src/                             # 后端源代码
│   ├── main/
│   │   ├── java/                     # Java源代码
│   │   └── resources/                # 配置文件
│   └── test/                        # 测试代码
├── pom.xml                          # Maven依赖配置
├── mvnw                             # Maven Wrapper
└── README.md                        # 项目总说明
```

## 🚀 快速开始

### 环境要求

- **Java 21** 或更高版本
- **Node.js 16.0** 或更高版本
- **Maven 3.6** 或更高版本
- **npm 或 yarn** 包管理器

### 后端启动

1. **克隆项目**
   ```bash
   git clone [项目地址]
   cd 企业规章制度问答机器人
   ```

2. **配置阿里云API密钥**
   - 在 `src/main/resources/application.yml` 中配置阿里云API密钥：
     ```yaml
     langchain4j:
       dashscope:
         api-key: ${DASHSCOPE_API_KEY:your-api-key-here}
     ```

3. **构建并运行后端**
   ```bash
   ./mvnw spring-boot:run
   ```
   后端服务将在 `http://localhost:8081` 启动

### 前端启动

1. **进入前端目录**
   ```bash
   cd ai-code-helper-frontend
   ```

2. **安装依赖**
   ```bash
   npm install
   ```

3. **启动开发服务器**
   ```bash
   npm run dev
   ```
   前端应用将在 `http://localhost:3000` 运行

## 📖 功能特点

### 核心功能
- 🤖 **智能对话**：基于大语言模型的智能问答，支持企业规章制度相关咨询
- 💬 **聊天界面**：现代化的聊天室风格界面，用户消息居右，AI回复居左
- 📱 **响应式设计**：完美适配桌面和移动设备
- ⚡ **实时流式响应**：AI回复实时显示，提供流畅的用户体验
- 🔄 **自动滚动**：新消息自动滚动到底部
- 🎨 **美观界面**：经过精心设计的UI，提供良好的用户体验
- 📝 **Markdown支持**：AI回复支持完整的Markdown格式，包括表格、列表等
- 🔍 **快捷查询**：提供请假流程、报销要求、考勤奖惩三个快捷查询按钮

### 技术特性
- **RAG增强**：结合企业规章制度文档，提供更准确的回答
- **会话记忆**：支持多轮对话，保持上下文连贯性
- **错误处理**：完善的错误处理机制，提供友好的错误提示
- **状态管理**：实时显示AI响应状态，包括加载中、已完成等

## 🔌 API接口

### 后端接口

- **基础URL**: `http://localhost:8081/api`
- **聊天接口**: `GET /ai/chat`
  - 参数：
    - `memoryId`: 聊天室ID（数字）
    - `message`: 用户消息（字符串）
  - 返回：SSE流式响应

### 前端API

前端通过`src/api/chatApi.js`文件与后端通信，主要包含以下方法：
- `chatWithSSE`: 建立SSE连接，接收AI流式回复
- `quickQuery`: 快捷查询方法，针对特定类型问题优化

## 🎨 界面展示

### 主要界面
1. **欢迎界面**：展示机器人欢迎信息和功能介绍
2. **聊天界面**：用户与AI进行实时对话
3. **快捷查询**：顶部三个快捷按钮，快速获取常见问题答案

### 设计特点
- **机器人图标动画**：欢迎页面中的机器人图标具有浮动动画效果
- **输入框优化**：圆角设计，高亮边框，提升输入体验
- **按钮美化**：醒目的主题色，悬浮放大效果
- **免责声明**：底部添加专业声明，体现系统严谨性

## 🛠️ 开发指南

### 添加新功能

1. **后端新功能**
   - 在`src/main/java`目录下创建新的控制器和服务类
   - 实现业务逻辑并添加相应的API接口
   - 配置RAG知识源（如需要）

2. **前端新功能**
   - 在`src/components/`目录下创建新组件
   - 在`src/utils/`目录下添加工具函数
   - 在`src/api/`目录下添加API接口

### 样式自定义

项目使用CSS3和Flexbox布局，样式分布在各个组件中。主要的样式变量：

- **主色调**：`#4A90E2`（科技蓝）
- **背景色**：`#f0f0f0`
- **消息气泡**：`#f1f3f4`（AI）、`#007bff`（用户）
- **免责声明**：`#999`（灰色），12px字体

## 🧪 浏览器支持

- Chrome 60+
- Firefox 60+
- Safari 12+
- Edge 79+

## 📄 许可证

本项目采用 MIT 许可证。

## 🤝 贡献指南

1. Fork 本项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 📞 联系方式

如有问题或建议，请联系开发团队。
