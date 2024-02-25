
# Spring Security: Пошаговый туториал.

В данной статье мы подробно рассмотрим процесс создания простого веб-приложения с использованием Spring Boot и Spring Security. В рамках этого проекта будет реализована система регистрации новых пользователей, а также механизм авторизации. Основное внимание уделено ограничению доступа к различным страницам сайта в зависимости от роли пользователя.

Цель данной статьи - детально продемонстрировать, как можно эффективно управлять доступом к различным разделам сайта в зависимости от роли конкретного пользователя.

## Концепция приложения 

- страницы доступные всем пользователям: главная, регистрации и логина;
- страница доступная для зарегистрированных пользователей: новости;
- страница доступная для администратора.

## Инструменты и технологии 
- JDK 8+
- Intellij Idea
- Spring (Spring Boot, Spring MVC, Spring Security)
- Hibernate
- JSP
- PostgreSQL

## Оглавление
1. Разъяснение ключевых аннотаций.
2. Как создать новый проект в IDE?
3. Организация структуры проекта (пакетов).
4. Добавление сущностей, контроллеров, сервисов, репозиториев и представлений.
5. Запуск приложения.

# 1. Разъяснение ключевых аннотаций.

**Controller:**
Контроллер - это специальный класс, применяемый в архитектуре MVC приложений. Похож на стандартный сервлет [HttpServlet](https://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/2.0/api/javax/servlet/http/HttpServlet.html), который взаимодействует с объектами HttpServletRequest и HttpServletResponse, но в контексте Spring Framework обладает расширенными возможностями. Контроллеры отвечают за обработку HTTP-запросов и направление данных между моделью и представлением.

**Repository:**
Аннотация Repository указывает, что класс предназначен для выполнения операций поиска, получения и сохранения данных. Эта аннотация может применяться для реализации паттерна [DAO (Data Access Object)](https://habr.com/ru/articles/262243/), обеспечивая эффективное взаимодействие с базой данных.

**Service:**
Аннотация Service используется для классов, которые выполняют роль сервиса и содержат бизнес-логику. Сервисы обеспечивают выполнение операций, связанных с обработкой данных, в соответствии с бизнес-требованиями.

**Configuration:**
Аннотация Configuration применяется к классам, которые определяют bean-компоненты. Это позволяет Spring контейнеру понять, что класс предоставляет конфигурацию приложения и содержит определения бинов.

**Autowired:**
Аннотация Autowired облегчает инъекцию зависимостей, позволяя Spring автоматически устанавливать значения полей. Это устраняет необходимость вручную управлять передачей экземпляров bean-компонентов между друг другом, делая процесс более простым и удобным.

### Немного информации о Spring Security

Самым фундаментальным объектом является [SecurityContextHolder](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/context/SecurityContextHolder.html). В нем хранится информация о текущем контексте безопасности приложения, который включает в себя подробную информацию о пользователе (принципале), работающим с приложением. Spring Security использует объект Authentication, пользователя авторизованной сессии.

«Пользователь» – это просто Object. В большинстве случаев он может быть
приведен к классу **UserDetails**. **UserDetails** можно представить, как адаптер между БД пользователей и тем что требуется Spring Security внутри **SecurityContextHolder**.

Для создания **UserDetails** используется интерфейс **UserDetailsService**, с единственным методом:

```java
UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
```

# 2. Создание нового проекта в IDE

В данном проекте будет использоваться сборка **Maven**

![](png/shot2024-02-25 162703.png)

Под **GroupId** подразумевается уникальный идентификатор компании (или ваше личное доменное имя), которая выпускает проект. **ArtefactId** – это просто название нашего проекта.

![](png/shot2024-02-25163111.png)

После того как наш проект будет создан, необходимо добавить следующие зависимости: 