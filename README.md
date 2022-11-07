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

![image](https://user-images.githubusercontent.com/84938597/200388021-b4b63679-00af-4153-bc6f-b6ce2f03eb5c.png)

### Вывод человека по id (get запрос (/person/id/$id)):

![image](https://user-images.githubusercontent.com/84938597/200388071-412f8e28-1b5f-4ce0-a839-165f864e42c3.png)

Если человек не будет найден, то будет выведено исключение в таком виде:

![image](https://user-images.githubusercontent.com/84938597/200388133-e59f7501-a822-4bfc-8a46-1ecf2fe61542.png)

### Вывод людей по имени name (get запрос (/person/name/$name)):

![image](https://user-images.githubusercontent.com/84938597/200388164-81d6283c-d0be-4d32-bf6f-41a3f5d29a2b.png)

### Вывод людей по фамилии lastName (get запрос (/person/lastName/$lastName)):

![image](https://user-images.githubusercontent.com/84938597/200388203-a49db165-74ad-4042-bc4e-b578e3d4e44b.png)

### Вставка нового человека (post запрос (/person)):

![image](https://user-images.githubusercontent.com/84938597/200388258-86177448-c027-454f-ad9e-c3068d344c7e.png)

После данного запроса выводится id новой персоны

### Люди в БД после добавления новой персоны:

![image](https://user-images.githubusercontent.com/84938597/200388307-1f6dd1ef-b30d-40b0-a3d4-1ebea1f19fb2.png)

### Обновление данных у персоны по id (put запрос (/person/$id)):

![image](https://user-images.githubusercontent.com/84938597/200388351-8e56d6a1-4e18-4c5d-8946-c996ce5b15a1.png)

### Люди в БД после обновления:

![image](https://user-images.githubusercontent.com/84938597/200388402-7ddf80de-0ab0-4676-8680-10f74e1dd115.png)

### Удаление человека по id (delete запрос (/person/$id)):

![image](https://user-images.githubusercontent.com/84938597/200388429-91112cf9-51a8-4251-95e6-c2b0b9717144.png)

### Люди в БД после обновления:

![image](https://user-images.githubusercontent.com/84938597/200388511-000b5698-9742-4d73-a9ea-fbd4fcf436c7.png)




