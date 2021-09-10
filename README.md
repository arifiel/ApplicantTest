# Тестовое задание

Данный репозиторий является мультимодульным проектом, содержащим 2 простых микросервиса. В качестве основы используются
Spring Boot и Spring Cloud.

Модуль **first** является основным и взаимодействует с пользователем по порту 8080.

Модуль **second** является вспомогательным для модуля **first** и предоставляет метод получения текущего времени 
сервера. Запускается на порту 8081. Считаем, что пользователь не знает о его существовании и не может с ним 
взаимодействовать.

Запуск осуществляется стандартными командами:
```
mvn clean compile
mvn spring-boot:run -pl first
mvn spring-boot:run -pl second
```

Работа с модулем first осуществляется вызовом контроллера приветствия с передачей имени:
```
curl http://localhost:8080/hello?name=John
```

В ответ вернётся соощение формата 
```
Hello {username}, current time: {timestamp}
```

**timestamp** в данном случае будет извлечён из второго микросервиса.

В тестовом задании считаем, что сеть работает идеально и всегда известен адрес микросервиса **second**, поэтому он 
указывается в конфигурации.

## Задача

Требуется модифицировать проект таким образом, чтобы была возможность упаковать оба сервиса в один jar-ник, который 
реализовывал бы весь функционал обоих сервисов и не открывал пользователю лишних методов, таких как получение текущего 
времени из сервиса **second**. Так же требуется сохранить текущую возможность запуска проекта в виде двух микросервисов.

Допускается использование любых дополнительных инструментов и библиотек.

## Решение

Упаковка сервисов first и second в сервис both осуществляется командой:
```
mvn clean install
```

Запуск осуществляется командой:
```
mvn spring-boot:run -pl both
```

Или запуском jar-файла:
```
java -jar both/target/both-0.0.1-SNAPSHOT.jar
```

Работа с сервисом both осуществляется аналогично с модулем first:
```
curl http://localhost:8080/hello?name=John
```

В ответ вернётся сообщение формата 
```
Hello {username}, current time: {timestamp}
```

Ресурс, доступный в сервисе second, будет недоступен.
При запросе:
```
curl http://localhost:8080/time
```

В ответ вернётся ошибка с кодом 404 .