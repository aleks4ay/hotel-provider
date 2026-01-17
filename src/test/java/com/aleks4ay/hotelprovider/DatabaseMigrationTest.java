package com.aleks4ay.hotelprovider;

import com.aleks4ay.hotelprovider.model.Hotel;
import com.aleks4ay.hotelprovider.model.Room;
import com.aleks4ay.hotelprovider.repocitory.HotelRepo;
import com.aleks4ay.hotelprovider.repocitory.RoomRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class DatabaseMigrationTest {

    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private HotelRepo hotelRepo;

    @Container
    static MSSQLServerContainer<?> sqlServerContainer = new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2019-latest")
            .acceptLicense();

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlServerContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlServerContainer::getUsername);
        registry.add("spring.datasource.password", sqlServerContainer::getPassword);
    }

    @Test
    void testRoomAfterMigrations() {
        Optional<Room> room = roomRepo.findById(1L);
        assertAll(
                () -> assertEquals(1L, room.map(Room::getRoomId).orElseThrow()),
                () -> assertEquals("Номер для студента тест", room.map(Room::getDescription).orElseThrow())
        );
    }

    @Test
    void testHotelAfterMigrations() {
        Optional<Hotel> hotel = hotelRepo.findById(1L);
        assertAll(
                () -> assertEquals("Hilton Miami Downtown", hotel.map(Hotel::getName).orElseThrow()),
                () -> assertEquals("3755 NW 78th Avenue, Doral, Маямі, FL 33166, США", hotel.map(Hotel::getAddress).orElseThrow()),
                () -> assertEquals("Цей першокласний готель...\nМайамі...".translateEscapes(), hotel.map(Hotel::getDescription).orElseThrow().translateEscapes())
        );
    }
}
