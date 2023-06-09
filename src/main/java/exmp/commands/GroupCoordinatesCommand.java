package exmp.commands;

import exmp.App;
import exmp.models.Coordinates;

import java.util.*;

/**
 * Команда group_counting_by_coordinates для группировки элементов коллекции по их значению coordinates
 */
public class GroupCoordinatesCommand implements exmp.commands.Command {

    /**
     * Возвращает название команды group_counting_by_coordinates.
     *
     * @return название команды group_counting_by_coordinates.
     */
    @Override
    public String getName() {
        return "group_counting_by_coordinates";
    }

    @Override
    public List<exmp.commands.ArgDescriptor> getArguments() {
        return new ArrayList<>();
    }

    /**
     * Возвращает описание команды group_counting_by_coordinates.
     *
     * @return описание команды group_counting_by_coordinates.
     */
    @Override
    public String getDescription() {
        return "сгруппировать элементы коллекции по значению поля coordinates, вывести количество элементов в каждой группе";
    }

    /**
     * Выполняет команду группировки элементов коллекции
     *
     * @param app объект приложения, над которым выполняется команда.
     * @param args массив аргументов команды.
     */
    @Override
    public boolean execute(App app, Object... args) {
        try {
            Map<String, Integer> coordinatesGroups = new HashMap<>();

            app.getProductRepository().findAll().forEach(product -> {
                Coordinates coordinates = product.getCoordinates();
                String coordinatesString = coordinates.toString();

                coordinatesGroups.put(coordinatesString, coordinatesGroups.getOrDefault(coordinatesString, 0) + 1);
            });

            System.out.println("Группировка элементов коллекции по координатам:");
            coordinatesGroups.forEach((coordinates, count) -> System.out.println("Координаты: " + coordinates + " | Количество элементов: " + count));

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
