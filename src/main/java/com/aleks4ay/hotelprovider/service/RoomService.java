package com.aleks4ay.hotelprovider.service;

import com.aleks4ay.hotelprovider.model.Image;
import com.aleks4ay.hotelprovider.model.Room;
import com.aleks4ay.hotelprovider.repocitory.RoomRepo;
import com.aleks4ay.hotelprovider.util.JpgToSvgGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepo roomRepo;

    public List<Room> findAll() {
        return roomRepo.findAll();
    }

    public Room findById(Long id) {
        return roomRepo.findById(id).orElse(null);
    }

    public Room save(Room room, MultipartFile image) {
        Image img = new Image();
        img.setFileName(JpgToSvgGenerator.renameToSvg(image.getOriginalFilename()));
        try {
            String svg = JpgToSvgGenerator.convertImageToSvg(image.getBytes());
            img.setSvgPicture(svg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        room.setImage(img);
        return roomRepo.save(room);
    }
}
