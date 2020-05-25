# Phone book

Scala REST API HTTP сервер для работы с телефонным справочником.

## Запуск

```
cd Phone-Book
sbt run
```

## API

- **POST** /phones/add

**Body:** json `{"name": "Michael", "phone": "+71234567890"}`

Добавить телефон и имя в справочник.

- **GET** /phones?name=&phone=%2B71234567890

Получить список записей справочника вместе с id в формате json.
Без параметров или с пустыми параметрами в запросе будут выведены все записи.
При использовании параметров выводятся записи, содержащие данные строки.
При двух параметрах используется логическое И.

- **PUT** /phones/:id

**Body:** json `{"name": "John", "phone": "+71234567890"}`

Изменить значения записи по данному id.

- **DELETE** /phones/:id

Удалить из справочника запись по id.