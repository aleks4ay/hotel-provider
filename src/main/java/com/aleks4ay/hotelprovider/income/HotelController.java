package com.aleks4ay.hotelprovider.income;

import com.aleks4ay.hotelprovider.model.Hotel;
import com.aleks4ay.hotelprovider.service.HotelService;
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
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    @SuppressWarnings("unused")
    public List<Hotel> getAll() {
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    @SuppressWarnings("unused")
    public Hotel getById(@PathVariable("id") Long id) {
        return hotelService.findById(id);
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @SuppressWarnings("unused")
    public Hotel create(@RequestPart("hotel") Hotel hotel,
                        @RequestPart("image") MultipartFile image) {
        return hotelService.save(hotel, image);
    }
}
