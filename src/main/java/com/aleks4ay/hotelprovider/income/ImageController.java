package com.aleks4ay.hotelprovider.income;

import com.aleks4ay.hotelprovider.model.Image;
import com.aleks4ay.hotelprovider.repocitory.ImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageRepo imageRepo;

    @GetMapping("/{id}")
    @SuppressWarnings("unused")
    public Image getById(@PathVariable("id") Long id) {
        return imageRepo.findById(id).orElse(null);
    }

    @GetMapping("/{id}/svg")
    @SuppressWarnings("unused")
    public String getBytesById(@PathVariable("id") Long id) {
        return imageRepo.findById(id)
                .map(Image::getSvgPicture)
                .orElse(null);
    }
}
