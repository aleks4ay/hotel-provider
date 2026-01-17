package com.aleks4ay.hotelprovider.service;

import com.aleks4ay.hotelprovider.model.Image;
import com.aleks4ay.hotelprovider.model.Hotel;
import com.aleks4ay.hotelprovider.repocitory.HotelRepo;
import com.aleks4ay.hotelprovider.util.JpgToSvgGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepo hotelRepo;

    public List<Hotel> findAll() {
        return hotelRepo.findAll();
    }

    public Hotel findById(Long id) {
        return hotelRepo.findById(id).orElse(null);
    }

    public Hotel save(Hotel room, MultipartFile image) {
        Image img = new Image();
        img.setFileName(JpgToSvgGenerator.renameToSvg(image.getOriginalFilename()));
        try {
            String svg = JpgToSvgGenerator.convertImageToSvg(image.getBytes());
            img.setSvgPicture(svg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        room.setImage(img);
        return hotelRepo.save(room);
    }
}
