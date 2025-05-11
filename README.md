# 智能制造系统

基于JavaEE的智能工厂监控和控制系统，具有实时监控、预测性维护和数据可视化功能。

## 功能特点

- **实时监控**：查看机器状态、温度和振动数据
- **预测性维护**：基于AI的潜在机器故障预测
- **用户管理**：基于角色的访问控制（管理员/操作员）
- **数据可视化**：传感器数据分析的交互式图表
- **RESTful API**：全面的机器数据和预测API

## 技术栈

- **后端**：Java 17, Spring Boot 3.x, Spring Data JPA, Spring Security
- **数据库**：H2数据库（内存型）
- **前端**：HTML5, CSS3, JavaScript, jQuery, Bootstrap 5
- **数据可视化**：Chart.js
- **服务器**：嵌入式Tomcat

## 入门指南

### 先决条件

- Java开发工具包(JDK) 17或更高版本
- Maven 3.6+或Gradle 7.0+
- Git（可选）

### 安装

1. 克隆仓库（或下载源代码）
   ```bash
   git clone https://https://github.com/Dreamlights001/JavaEE_IM.git
   cd smart-manufacturing
   ```

2. 构建项目
   ```bash
   mvn clean install
   ```

3. 运行应用程序
   ```bash
   mvn spring-boot:run
   ```

4. 访问应用程序
   打开您的浏览器并导航至 `http://localhost:8080/login.html`

### 默认凭据

- **管理员**：用户名: `admin`, 密码: `admin`
- **操作员**：用户名: `operator`, 密码: `operator`

## 项目结构

- `src/main/java/com/example/smartmanufacturing/` - Java源代码
  - `model/` - 实体类
  - `repository/` - 数据访问接口
  - `service/` - 业务逻辑
  - `controller/` - REST API端点
  - `config/` - 配置类
- `src/main/resources/` - 应用资源
  - `static/` - 前端文件（HTML, CSS, JS）
  - `application.properties` - 应用配置

## API端点

| 端点 | 方法 | 描述 |
|----------|--------|-------------|
| `/api/machines` | GET | 获取所有机器 |
| `/api/machines/{id}` | GET | 获取特定机器详情 |
| `/api/machines/{id}/sensorData` | GET | 获取机器的传感器数据 |
| `/api/machines/{id}/predictions` | GET | 获取机器的预测数据 |
| `/api/sensorData` | GET | 获取所有传感器数据 |
| `/api/predictions` | GET | 获取所有预测数据 |

## 截图

（在此处添加您的应用程序截图）

## 未来增强

- 更准确预测的机器学习模型
- 使用WebSockets进行实时数据流传输
- 用于移动监控的移动应用程序
- 与实际物联网设备的集成
- 高级分析和报告

## 许可证

本项目采用MIT许可证 - 详情请参阅LICENSE文件。

## 致谢

- Spring Framework团队
- Bootstrap团队
- Chart.js贡献者 