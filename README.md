# RestApi

Rest Api для работы с БД:

1. Есть сущность Person c тремя полями - id, name, lastName. Организовать для нее CRUD операции через рест апи
2. Cущность должна храниться в СУБД в таблице Person
3. Для работы с БД - Spring JDBC

рест апи вида:
person/name/{boris} - все персоны с именем boris
person/lastName/{schukin} - все персоны с фамиилией Щукин
person/id/{666}- одна персона с id = 666

# Примеры запросов в Postman

### Вывод всех людей в БД (get запрос (/person)):

![img_1.png](img_1.png)

### Вывод человека по id (get запрос (/person/id/$id)):

![img_2.png](img_2.png)

Если человек не будет найден, то будет выведено исключение в таком виде:

![img_16.png](img_16.png)

### Вывод людей по имени name (get запрос (/person/name/$name)):

![img_3.png](img_3.png)

### Вывод людей по фамилии lastName (get запрос (/person/lastName/$lastName)):

![img_4.png](img_4.png)

### Вставка нового человека (post запрос (/person)):

![img_9.png](img_9.png)

После данного запроса выводится id новой персоны

### Люди в БД после добавления новой персоны:

![img_10.png](img_10.png)

### Обновление данных у персоны по id (put запрос (/person/$id)):

![img_11.png](img_11.png)

### Люди в БД после обновления:

![img_12.png](img_12.png)

### Удаление человека по id (delete запрос (/person/$id)):

![img_14.png](img_14.png)

### Люди в БД после обновления:

![img_15.png](img_15.png)



