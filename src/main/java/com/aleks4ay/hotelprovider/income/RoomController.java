package com.aleks4ay.hotelprovider.income;

import com.aleks4ay.hotelprovider.model.Room;
import com.aleks4ay.hotelprovider.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    @SuppressWarnings("unused")
    public List<Room> getAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    @SuppressWarnings("unused")
    public Room getById(@PathVariable("id") Long id) {
        return roomService.findById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SuppressWarnings("unused")
    public Room create(@RequestPart("room") Room room,
                       @RequestPart("image") MultipartFile image) {
        return roomService.save(room, image);
    }
}
