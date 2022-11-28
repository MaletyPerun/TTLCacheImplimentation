
## Цель задания – разработать приложение имплементацию in-memory Redis кеша.

-------

### ТЗ:

* Писать код можно на любом языке программирования
* Предоставить инструкцию по запуску приложения. В идеале (но не обязательно) – использовать контейнеризацию с возможностью запустить проект командой docker-compose up
* Финальную версию нужно выложить на github.com (просьба не делать форк этого репозитория, дабы не плодить плагиат)

### Необходимы функционал:

- Клиент и сервер tcp(telnet)/REST API
- Key-value хранилище строк, списков, словарей
- Возможность установить TTL на каждый ключ
- Реализовать операторы: GET, SET, DEL, KEYS
- Реализовать покрытие несколькими тестами функционала 

### Дополнительно (необязательно):

- Реализовать операторы: HGET, HSET, LGET, LSET
- Реализовать сохранение на диск
- Масштабирование (на серверной или на клиентское стороне)
- Авторизация
- Нагрузочные тесты

-------

API:

#### получить кэш с ключом {key}
`curl --location --request GET 'http://localhost:8080/GET?key={key}`

#### записать кэш с ключом {TestKey} и значением {Test message}
`curl --location --request POST 'http://localhost:8080/SET' \
--header 'Content-Type: application/json' \
--data-raw '{
"count":"testCount",
"strLine":"{TestKey} {Test message}"
}'`

#### получить список всех совпадений значений в кеше по ключу {key}
`curl --location --request GET 'http://localhost:8080/KEYS?key={key}'`

#### удалить кеш по ключу {key}
`curl --location --request DELETE 'http://localhost:8080/DEL?key={key}'`

###### время жизни объектов (TTL) состовляет 20 секунд
