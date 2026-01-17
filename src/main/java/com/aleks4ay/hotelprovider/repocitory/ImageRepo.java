package com.aleks4ay.hotelprovider.repocitory;

import com.aleks4ay.hotelprovider.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
}
