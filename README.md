# Employess

Тестовое задание по теме «Объектно-ориентированное программирование на языке Java с применением циклических алгоритмов обработки коллекций объектов и работой с файловым вводом и выводом»

Указания к выполнению задания:
•	Все классы должны удовлетворять Code Conventions for the Java Programming Language и принципам SOLID
•	В каждом классе должны быть описаны конструкторы, необходимые для решения конкретной задачи и инициализирующие поля классов, методы получения и установки значений по логике программы, метод toString, методы hashCode и equals

В IT-компании ST каждый сотрудник имеет рейтинг в виде целого числа в диапазоне от 0 до 100, сотрудники работают в департаментах, имеющих номер в виде целого числа от 1 до 20, каждый департамент содержит максимально 30 отделов.
Информация о сотрудниках представлена в виде .csv файла с последовательностью данных (одна из перечисленных):
•	Фамилия;Имя;номер_отдела;рейтинг
•	Фамилия;Имя;номер_департамента;рейтинг

Указание:
Разработать схему данных и коллекций, необходимых для рационального решения поставленных задач и согласовать ее со своим руководителем проекта

Задачи:
1.	load
Произвести сохранение объектов из .csv файла в соответствующую коллекцию данных, удовлетворяющую решениям нижестоящих задач. На основании загруженной коллекции производить вычисления
2.	getCoolestEmployees
Определить самых успешных сотрудников по каждому отделу
3.	getMaxScores
Вернуть в виде коллекции рейтинг самых успешных сотрудников в каждом отделе
4.	getAverageScores
Вернуть в виде коллекции средний рейтинг сотрудников по каждому отделу
5.	getCountCoolestEmployees
Определить количество самых успешных сотрудников по каждому отделу
6.	getCoolestEmployeesAll
Определить самых успешных сотрудников по всем отделам
7.	getSubCoolestEmployees
Определить сотрудников, не ставших самыми успешными, но следующих сразу же после успешных по каждому отделу
8.	getMaxScoresSubCoolestEmployees
Определить рейтинг сотрудников, не ставших самыми успешными, но следующих сразу же после успешных по каждому отделу и по всем отделам
9.	getMaxScoreAll
Определить рейтинг самых успешных сотрудников по всем отделам
10.	getMaxCountDepartments
Вернуть в порядке возрастания номера департаментов, где работает больше всего сотрудников
11.	getMinCountDepartments
Вернуть в порядке возрастания номера департаментов, где работает меньше всего сотрудников
12.	sort
Отсортировать коллекцию сотрудников по фамилии, при равенстве фамилии по имени
13.	sort
Отсортировать коллекцию сотрудников:
•	По убыванию рейтинга
•	При равных значениях рейтинга - по фамилии в лексикографическом порядке
•	При совпадении рейтинга и фамилии - по имени в лексикографическом порядке
Объединить сортировки из 12 и 13 пунктов в единый метод, используя перечисления(enums)
14.	departmentCoolestEmployees
Вычислить номера департаментов в порядке возрастания, в которых есть хотя бы один сотрудник, ставший самым успешным по всем департаментам
15.	greatAverageScoreDepartments
Вычислить в порядке возрастания номера департаментов, средний рейтинг сотрудников которых выше, чем средний рейтинг всех сотрудников в компании. То есть необходимо вычислить средний рейтинг для каждого департамента отдельно и средний рейтинг по всем департаментам вместе взятых. Модернизировать метод из п. 4 таким образом, чтобы он возвращал среднее не только по отделам, но и по департаментам
16.	maxAverageScoreDepartments
Вычислить в порядке возрастания номера департаментов, средний рейтинг сотрудников которых максимален
17.	sortByCountEmployees
Вернуть номера департаментов в порядке убывания количества сотрудников, работающих в этих департаментах. Если в двух департаментах работает одинаковое количество сотрудников, то их номера выводятся в порядке возрастания номера департамента
18.	sortByAverageScores
Вернуть номера департаментов в порядке убывания среднего рейтинга сотрудников, работающих в этих департаментах. Если в двух департаментах имеется одинаковый средний рейтинг сотрудников, то их номера выводятся в порядке возрастания номера департамента
19.	maxCountCoolestEmployeeDepartments
Вернуть в порядке возрастания номера департаментов, из которых наибольшее количество сотрудников стало самыми успешными

