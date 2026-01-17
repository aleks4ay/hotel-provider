package com.aleks4ay.hotelprovider.repocitory;

import com.aleks4ay.hotelprovider.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
}
