package com.haryo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haryo.dto.BannerDto;
import com.haryo.model.Banner;
import com.haryo.model.response.HttpResponseModel;
import com.haryo.repository.BannerRepository;

@RestController
@RequestMapping("/banner")
public class BannerController {
	@Autowired
	private BannerRepository bannerRepo;
	
	
	@GetMapping
    public HttpResponseModel<List<BannerDto>> getPublicBanners() {
        HttpResponseModel<List<BannerDto>> result = new HttpResponseModel<>();

        try {
            List<BannerDto> banners = getBannersFromDatabase(); 

            result.setStatus(0); 
            result.setMessage("Sukses");
            result.setData(banners);

            return result;
        } catch (Exception e) {
            result.setStatus(500); 
            result.setMessage("Terjadi kesalahan saat mengambil banners");
            result.setData(null);

            return result;
        }
    }

    private List<BannerDto> getBannersFromDatabase() {
        // Implementasikan logika untuk mengambil data banner dari database (gunakan repository)
        Iterable<Banner> bannersIterable = bannerRepo.findAll();
        
        List<Banner> banners = StreamSupport.stream(bannersIterable.spliterator(), false)
                .collect(Collectors.toList());

        return banners.stream()
                .map(this::convertToBannerDto)
                .collect(Collectors.toList());
    }

    private BannerDto convertToBannerDto(Banner banner) {
        return new BannerDto(
                banner.getBannerName(),
                banner.getBannerImage(),
                banner.getDescription()
        );
    }
}