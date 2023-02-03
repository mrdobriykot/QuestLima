# JavaRush project module №3 - QUEST

Пример работы проекта: http://194.87.92.135:8080/javarush-project-quest/

Тема: использование сервлетов, JSP, JSTL. Использование баз данных не требуется, но в этом проекте используется MySQL.

## Задание

![задание с джавараш](https://i.imgur.com/jXTXGTy.png)

## Запуск и конфигурация
- Запустить MySQL сервер, выполнить sql-скрипт mysql_db.sql (src/main/resources/mysql_db.sql)
- Если нет MySQL, то нужно закомментировать строки
> MYSQL_REPOS
> 
> HIBERNATE_REPOS
- в файлах
> repository/ChooserQuestsRepository.java
> 
> repository/ChooserUserRepository.java
- Используем Tomcat 9 версии
- В случае указания application context (конфигурация Tomcat (вкладка deployment)) отличным от "/" или "", необходимо в js/code.js в константе APPLICATION_CONTEXT_PATH 
установить значение как в конфигурации Tomcat.
- > Например для развёртывание сайта по адресу http://site.ru/ поле application context оставить пустым или 
установить значение "/", тогда  в js/code.js APPLICATION_CONTEXT_PATH=""
- > Для развёртывание сайта по адресу http://site.ru/my-super-quest в поле application context установить значение 
"/my-super-quest", тогда в js/code.js APPLICATION_CONTEXT_PATH="/my-super-quest"
- В hikari.properties и hibernate.cfg.xml указать свои настройки БД
- При желании редактируем настройки в settings.json
- Запускаем сервер

## Особенности
- Есть возможность переключаться между 3-мя "базами данных": HashMap, JSON и MySQL.
- Для MySQL используется как JDBC, так и Hibernate.
- Сохранение игр в сессии
- Для данных из JSON файлов желательно отключить кэширование в настройках Tomcat:
  > - В [TOMCAT_FOLDER]/conf/context.xml
  > - Добавить запрет на кеширование
  > - Resources cachingAllowed="false"
  > - или уменьшить время нахождения ресурсов в кеше в мс
  > - Resources cacheTtl="100"
- в противном случае при редактировании данных квестов или пользователей данные не будут сразу обновляться, а лишь 
спустя 5 секунд (по умолчанию настройка в tomcat?)

## Требуется написать
- Создание квестов
- Проверку вводимых символов в формах на JavaScript
- JUnit тестирование
- Репозиторий квестов под hibernate

## Что сделано
- Регистрация/авторизация пользователей с возможностью использования ролей (admin, editor, user, guest) и выбора аватара.
- Возможность удаления/редактирования пользователей/квестов согласно роли
- Добавлена возможность изменять рейтинг квеста
- Статистика игроков и квестов
- Логирование
- Загрузка/удаление изображения квеста. Можно добавить и для пользователей при необходимости
- Постраничная навигация на страницах квестов и пользователей
- В админ-панели можно "на ходу" переключать базы данных для квестов и для пользователей. Сделано только в учебных целях.

## Скриншоты
![Скриншоты проекта](https://i.imgur.com/LwUUJQF.png)
![Скриншоты проекта](https://i.imgur.com/totKYeZ.png)
![Скриншоты проекта](https://i.imgur.com/kKJMpmx.png)
![Скриншоты проекта](https://i.imgur.com/Np1xMWU.png)
![Скриншоты проекта](https://i.imgur.com/RBBlCrm.png)
![Скриншоты проекта](https://i.imgur.com/1AKv4XC.png)
![Скриншоты проекта](https://i.imgur.com/2P7gDru.png)