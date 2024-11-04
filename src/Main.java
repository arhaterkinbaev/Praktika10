class RoomBookingSystem {
    public void bookRoom(String customerName, String roomType) {
        System.out.println("Номер забронирован для " + customerName + " с типом номера: " + roomType);
    }

    public void checkAvailability(String roomType) {
        System.out.println("Проверка доступности для типа номера: " + roomType);
    }

    public void cancelBooking(String customerName) {
        System.out.println("Бронирование отменено для " + customerName);
    }
}

class RestaurantSystem {
    public void reserveTable(String customerName, int numberOfPeople) {
        System.out.println("Стол забронирован для " + customerName + " на " + numberOfPeople + " человек.");
    }

    public void orderFood(String customerName, String foodItem) {
        System.out.println(customerName + " заказал еду: " + foodItem);
    }
}

class EventManagementSystem {
    public void organizeEvent(String eventName, int numberOfParticipants) {
        System.out.println("Мероприятие организовано: " + eventName + " для " + numberOfParticipants + " участников.");
    }

    public void orderEquipment(String eventName, String equipment) {
        System.out.println("Оборудование заказано для мероприятия: " + eventName + ", оборудование: " + equipment);
    }
}

class CleaningService {
    public void scheduleCleaning(String roomNumber) {
        System.out.println("Уборка запланирована для номера: " + roomNumber);
    }

    public void performCleaning(String roomNumber) {
        System.out.println("Уборка выполнена для номера: " + roomNumber);
    }
}
class HotelFacade {
    private RoomBookingSystem roomBookingSystem;
    private RestaurantSystem restaurantSystem;
    private EventManagementSystem eventManagementSystem;
    private CleaningService cleaningService;

    public HotelFacade() {
        this.roomBookingSystem = new RoomBookingSystem();
        this.restaurantSystem = new RestaurantSystem();
        this.eventManagementSystem = new EventManagementSystem();
        this.cleaningService = new CleaningService();
    }

    public void bookRoomWithServices(String customerName, String roomType, String foodItem) {
        roomBookingSystem.checkAvailability(roomType);
        roomBookingSystem.bookRoom(customerName, roomType);
        restaurantSystem.orderFood(customerName, foodItem);
        cleaningService.scheduleCleaning("101");
    }

    public void organizeEventWithRooms(String eventName, int numberOfParticipants, String equipment) {
        eventManagementSystem.organizeEvent(eventName, numberOfParticipants);
        eventManagementSystem.orderEquipment(eventName, equipment);
        roomBookingSystem.bookRoom("Участник мероприятия", "Стандартный номер");
    }

    public void reserveTableWithTaxi(String customerName, int numberOfPeople) {
        restaurantSystem.reserveTable(customerName, numberOfPeople);
        System.out.println("Такси вызвано для " + customerName);
    }

    public void cancelRoomBooking(String customerName) {
        roomBookingSystem.cancelBooking(customerName);
    }

    public void organizeCleaning(String roomNumber) {
        cleaningService.performCleaning(roomNumber);
    }
}

public class Main {
    public static void main(String[] args) {
        HotelFacade hotelFacade = new HotelFacade();

        hotelFacade.bookRoomWithServices("Алиса", "Двухместный номер", "Пицца");
        hotelFacade.organizeEventWithRooms("Ежегодная конференция", 50, "Проектор");
        hotelFacade.reserveTableWithTaxi("Боб", 4);
        hotelFacade.cancelRoomBooking("Алиса");
        hotelFacade.organizeCleaning("101");
    }
}
