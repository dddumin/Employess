package repository;

import model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EmployeeRepository {
    private HashMap<Integer, ArrayList<Employee>> map;

    public EmployeeRepository(String fileName) throws IOException {
        load(fileName);
    }

    /**
     * 1.	load
     * Произвести сохранение объектов из .csv файла в соответствующую коллекцию данных, удовлетворяющую решениям нижестоящих задач.
     * На основании загруженной коллекции производить вычисления
     */

    private void load(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            this.map = new HashMap<>();
            String[] mass = reader.readLine().split(";");
            while (reader.ready()) {
                String[] line = reader.readLine().split(";");
                Employee employee;
                int key;
                if (mass[2].equals("номер_отдела")) {
                    employee = new Employee(line[1], line[0], Integer.parseInt(line[3]), Integer.parseInt(line[2]), 0);
                    key = employee.getNumberSection();
                } else {
                    employee = new Employee(line[1], line[0], Integer.parseInt(line[3]), Integer.parseInt(line[2]));
                    key = employee.getNumberDepartment();
                }
                if (!this.map.containsKey(key))
                    this.map.put(key, new ArrayList<>());
                this.map.get(key).add(employee);
            }
        }
    }

    /**
     * 2.	getCoolestEmployees
     * Определить самых успешных сотрудников по каждому отделу
     *
     * @return
     */

    public HashMap<Integer, ArrayList<Employee>> getCoolestEmployees() {
        HashMap<Integer, ArrayList<Employee>> mapCoolest = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet()) {
            int key = entry.getKey();
            int a = 0;
            mapCoolest.put(key, new ArrayList<>());
            for (Employee employee : entry.getValue()) {
                if (employee.getRating() > a)
                    a = employee.getRating();
            }
            for (Employee employee : entry.getValue()) {
                if (employee.getRating() == a)
                    mapCoolest.get(key).add(employee);
            }
        }
        return mapCoolest;
    }

    /**
     * 3.	getMaxScores
     * Вернуть в виде коллекции рейтинг самых успешных сотрудников в каждом отделе
     *
     * @return
     */

    public HashMap<Integer, Integer> getMaxScores() {
        HashMap<Integer, Integer> mapMaxScores = new HashMap<>();
        HashMap<Integer, ArrayList<Employee>> mapCoolest = this.getCoolestEmployees();
        for (Map.Entry<Integer, ArrayList<Employee>> entry : mapCoolest.entrySet()) {
            mapMaxScores.put(entry.getKey(), entry.getValue().get(0).getRating());
        }
        return mapMaxScores;
    }

    /**
     * 4.	getAverageScores
     * Вернуть в виде коллекции средний рейтинг сотрудников по каждому отделу
     *
     * @return
     */

    public HashMap<Integer, Double> getAverageScores() {
        HashMap<Integer, Double> mapAverageScores = new HashMap<>();
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet()) {
            double count = entry.getValue().size();
            mapAverageScores.put(entry.getKey(), 0.00);
            for (Employee employee : entry.getValue()) {
                mapAverageScores.replace(entry.getKey(), mapAverageScores.get(entry.getKey()) + employee.getRating() / count);
            }
        }
        return mapAverageScores;
    }

    /**
     * 5.	getCountCoolestEmployees
     * Определить количество самых успешных сотрудников по каждому отделу
     *
     * @return
     */

    public HashMap<Integer, Integer> getCountCoolestEmployees() {
        HashMap<Integer, Integer> mapCountCoolest = new HashMap<>();
        HashMap<Integer, ArrayList<Employee>> mapCoolest = this.getCoolestEmployees();
        for (Map.Entry<Integer, ArrayList<Employee>> entry : mapCoolest.entrySet()) {
            mapCountCoolest.put(entry.getKey(), entry.getValue().size());
        }
        return mapCountCoolest;
    }

    /**
     * 6.	getCoolestEmployeesAll
     * Определить самых успешных сотрудников по всем отделам
     *
     * @return
     */

    public ArrayList<Employee> getCoolestEmployeesAll() {
        HashMap<Integer, ArrayList<Employee>> mapCoolest = this.getCoolestEmployees();
        ArrayList<Employee> employees = new ArrayList<>();
        int a = 0;
        for (Map.Entry<Integer, ArrayList<Employee>> entry : mapCoolest.entrySet()) {
            if (entry.getValue().get(0).getRating() > a)
                a = entry.getValue().get(0).getRating();
        }
        for (Map.Entry<Integer, ArrayList<Employee>> entry : mapCoolest.entrySet()) {
            if (entry.getValue().get(0).getRating() == a)
                employees.addAll(entry.getValue());
        }
        return employees;
    }

    /**
     * 7.	getSubCoolestEmployees
     * Определить сотрудников, не ставших самыми успешными, но следующих сразу же после успешных по каждому отделу
     *
     * @return
     */

    /*public Map.Entry<HashMap<Integer, ArrayList<Employee>>, Integer> getSubCoolestEmployees() {
        HashMap<Integer, ArrayList<Employee>> mapSubCoolest = new HashMap<>();
        HashMap<Integer, Integer> mapMaxScores = this.getMaxScores();
        int b = 0;
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet()) {
            int a = 0;
            mapSubCoolest.put(entry.getKey(), new ArrayList<>());
            for (Employee employee : entry.getValue()) {
                if (employee.getRating() > a && employee.getRating() < mapMaxScores.get(entry.getKey()))
                    a = employee.getRating();
                if (a>b)
                    b = a;
            }
            for (Employee employee : entry.getValue()) {
                if (employee.getRating() == a)
                    mapSubCoolest.get(entry.getKey()).add(employee);
            }
        }
        return new AbstractMap.SimpleEntry<>(mapSubCoolest, b);
    }*/
    public HashMap<Integer, ArrayList<Employee>> getSubCoolestEmployees() {
        HashMap<Integer, ArrayList<Employee>> mapSubCoolest = new HashMap<>();
        HashMap<Integer, Integer> mapMaxScores = this.getMaxScores();
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet()) {
            int a = 0;
            mapSubCoolest.put(entry.getKey(), new ArrayList<>());
            for (Employee employee : entry.getValue()) {
                if (employee.getRating() > a && employee.getRating() < mapMaxScores.get(entry.getKey()))
                    a = employee.getRating();
            }
            for (Employee employee : entry.getValue()) {
                if (employee.getRating() == a)
                    mapSubCoolest.get(entry.getKey()).add(employee);
            }
        }
        return mapSubCoolest;
    }

    /**
     * 8.	getMaxScoresSubCoolestEmployees
     * Определить рейтинг сотрудников, не ставших самыми успешными, но следующих сразу же после успешных по каждому отделу и по всем отделам
     *
     * @return
     */
    public Map.Entry<HashMap<Integer, Integer>, Integer> getMaxScoresSubCoolestEmployees() {
        HashMap<Integer, Integer> mapRatingSubCoolest = new HashMap<>();
        HashMap<Integer, ArrayList<Employee>> mapSubCoolest = this.getSubCoolestEmployees();
        int b = 0;
        for (Map.Entry<Integer, ArrayList<Employee>> entry : mapSubCoolest.entrySet()) {
            if (entry.getValue().size() == 0)
                continue;
            mapRatingSubCoolest.put(entry.getKey(), entry.getValue().get(0).getRating());
            if (entry.getValue().get(0).getRating() > b)
                b = entry.getValue().get(0).getRating();
        }
        return new AbstractMap.SimpleImmutableEntry<>(mapRatingSubCoolest, b);
    }

    /**
     * 9.	getMaxScoreAll
     * Определить рейтинг самых успешных сотрудников по всем отделам
     *
     * @return
     */

    public Integer getMaxScoreAll() {
        return this.getCoolestEmployeesAll().get(0).getRating();
    }

    /**
     * 10.	getMaxCountDepartments
     * Вернуть в порядке возрастания номера департаментов, где работает больше всего сотрудников
     */

    //заново
    public HashSet<Integer> getMaxCountDepartments() {
        HashSet<Integer> setCountDepartments = new HashSet<>();
        int a = 0;
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet()) {
            if (entry.getValue().size() > a)
                a = entry.getValue().size();
        }
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet()) {
            if (entry.getValue().size() == a)
                setCountDepartments.add(entry.getKey());
        }
        return setCountDepartments;
    }

    /**
     * 11.	getMinCountDepartments
     * Вернуть в порядке возрастания номера департаментов, где работает меньше всего сотрудников
     *
     * @return
     */
    //заново
    public HashSet<Integer> getMinCountDepartments() {
        HashSet<Integer> setCountDepartments = new HashSet<>();
        int a = Integer.MAX_VALUE;
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet()) {
            if (entry.getValue().size() < a)
                a = entry.getValue().size();
        }
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet()) {
            if (entry.getValue().size() == a)
                setCountDepartments.add(entry.getKey());
        }
        return setCountDepartments;
    }

    /**
     * 12.	sort
     * Отсортировать коллекцию сотрудников по фамилии, при равенстве фамилии по имени
     * 13.	sort
     * Отсортировать коллекцию сотрудников:
     * •	По убыванию рейтинга
     * •	При равных значениях рейтинга - по фамилии в лексикографическом порядке
     * •	При совпадении рейтинга и фамилии - по имени в лексикографическом порядке
     * Объединить сортировки из 12 и 13 пунктов в единый метод, используя перечисления(enums)
     *
     * @param key
     * @return
     */

    public ArrayList<Employee> sort(Key key) {
        ArrayList<Employee> arrayList = new ArrayList<>();
        for (Map.Entry<Integer, ArrayList<Employee>> entry : this.map.entrySet())
            arrayList.addAll(entry.getValue());

        arrayList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (key == Key.N) {
                    if (o1.getSecondName().equals(o2.getSecondName()))
                        return o1.getFirstName().compareTo(o2.getFirstName());
                    return o1.getSecondName().compareTo(o2.getSecondName());
                }
                else{
                    if (o1.getRating() == o2.getRating()) {
                        if (o1.getSecondName().equals(o2.getSecondName()))
                            return o1.getFirstName().compareTo(o2.getFirstName());
                        return o1.getSecondName().compareTo(o2.getSecondName());
                    }
                    return -Integer.compare(o1.getRating(), o2.getRating());
                }
            }
        });
        return arrayList;
    }

    /**
     * 14.	departmentCoolestEmployees
     * Вычислить номера департаментов в порядке возрастания, в которых есть хотя бы один сотрудник, ставший самым успешным по всем департаментам
     *
     * @return
     */

    public ArrayList<Integer> departmentCoolestEmployees() {
        HashSet<Integer> linkedHashSet = new HashSet<>();
        ArrayList<Employee> employees = this.getCoolestEmployeesAll();
        for (Employee employee : employees) {
            linkedHashSet.add(employee.getNumberDepartment());
        }
        return new ArrayList<>(linkedHashSet);
    }

    /**
     * 15.	greatAverageScoreDepartments
     * Вычислить в порядке возрастания номера департаментов, средний рейтинг сотрудников которых выше, чем средний рейтинг всех сотрудников в компании.
     * То есть необходимо вычислить средний рейтинг для каждого департамента отдельно и средний рейтинг по всем департаментам вместе взятых.
     * Модернизировать метод из п.4 таким образом, чтобы он возвращал среднее не только по отделам, но и по департаментам
     *
     * @return
     */

    public HashSet<Integer> greatAverageScoreDepartments() {
        HashMap<Integer, Double> mapAverageScores = this.getAverageScores();
        HashSet<Integer> hashSet = new HashSet<>();
        double averageScore = 0;
        for (Map.Entry<Integer, Double> entry : mapAverageScores.entrySet()) {
            averageScore += entry.getValue() / mapAverageScores.size();
        }
        for (Map.Entry<Integer, Double> entry : mapAverageScores.entrySet()) {
            if (entry.getValue() > averageScore)
                hashSet.add(entry.getKey());
        }
        return hashSet;
    }

    /**
     * 16.	maxAverageScoreDepartments
     * Вычислить в порядке возрастания номера департаментов, средний рейтинг сотрудников которых максимален
     *
     * @return
     */

    public ArrayList<Integer> maxAverageScoreDepartments() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        HashMap<Integer, Double> averageScores = this.getAverageScores();
        double a = 0;
        for (Map.Entry<Integer, Double> entry : averageScores.entrySet()) {
            if (entry.getValue() > a)
                a = entry.getValue();
        }
        for (Map.Entry<Integer, Double> entry : averageScores.entrySet()) {
            if (entry.getValue() == a)
                arrayList.add(entry.getKey());
        }
        arrayList.sort(null);
        return arrayList;
    }

    /**
     * 17.	sortByCountEmployees
     * Вернуть номера департаментов в порядке убывания количества сотрудников, работающих в этих департаментах.
     * Если в двух департаментах работает одинаковое количество сотрудников, то их номера выводятся в порядке возрастания номера департамента
     *
     * @return
     */

    public ArrayList<Map.Entry<Integer, ArrayList<Employee>>> sortByCountEmployees() {
        ArrayList<Map.Entry<Integer, ArrayList<Employee>>> arrayList = new ArrayList<>(this.map.entrySet());
        arrayList.sort(new Comparator<Map.Entry<Integer, ArrayList<Employee>>>() {
            @Override
            public int compare(Map.Entry<Integer, ArrayList<Employee>> o1, Map.Entry<Integer, ArrayList<Employee>> o2) {
                if (o1.getValue().size() == o2.getValue().size())
                    return Integer.compare(o1.getKey(), o2.getKey());
                return Integer.compare(o1.getValue().size(), o2.getValue().size());
            }
        });
        return arrayList;
    }


    /**
     * 18.	sortByAverageScores
     * Вернуть номера департаментов в порядке убывания среднего рейтинга сотрудников, работающих в этих департаментах.
     * Если в двух департаментах имеется одинаковый средний рейтинг сотрудников, то их номера выводятся в порядке возрастания номера департамента
     */

    public ArrayList<Map.Entry<Integer, Double>> sortByAverageScores() {
        ArrayList<Map.Entry<Integer, Double>> arrayList = new ArrayList<>(this.getAverageScores().entrySet());
        arrayList.sort(new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                if (o1.getValue().equals(o2.getValue()))
                    return Integer.compare(o1.getKey(), o2.getKey());
                return -Double.compare(o1.getValue(), o2.getValue());
            }
        });
        return arrayList;
    }

    /**
     * 19.	maxCountCoolestEmployeeDepartments
     * Вернуть в порядке возрастания номера департаментов, из которых наибольшее количество сотрудников стало самыми успешными
     */

    public HashSet<Integer> maxCountCoolestEmployeeDepartments() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        ArrayList<Employee> coolestEmployeesAll = this.getCoolestEmployeesAll();
        int a = 0;
        for (Employee employee : coolestEmployeesAll) {
            if (!hashMap.containsKey(employee.getNumberDepartment()))
                hashMap.put(employee.getNumberDepartment(), 0);
            hashMap.replace(employee.getNumberDepartment(), hashMap.get(employee.getNumberDepartment()) + 1);
            if (hashMap.get(employee.getNumberDepartment()) > a)
                a = hashMap.get(employee.getNumberDepartment());
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (Employee employee : coolestEmployeesAll) {
            if (hashMap.get(employee.getNumberDepartment()) == a)
                hashSet.add(employee.getNumberDepartment());
        }
        return hashSet;
    }






    @Override
    public String toString() {
        return "EmployeeRepository{" +
                "map=" + map +
                '}';
    }
}
