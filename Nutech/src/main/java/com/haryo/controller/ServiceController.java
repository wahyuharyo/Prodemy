package com.haryo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haryo.dto.ServiceDto;
import com.haryo.model.Service;
import com.haryo.model.response.HttpResponseModel;
import com.haryo.repository.ServiceRepository;

@RestController
@RequestMapping("/services")
public class ServiceController {
	@Autowired
    private ServiceRepository serviceRepo;

    @GetMapping
    public HttpResponseModel<List<ServiceDto>> getServices(Authentication authentication) {
        HttpResponseModel<List<ServiceDto>> result = new HttpResponseModel<>();

        if (authentication == null) {
            result.setStatus(108); // Unauthorized
            result.setMessage("Token tidak valid atau kadaluwarsa");
            return result;
        }

        List<ServiceDto> services = StreamSupport.stream(serviceRepo.findAll().spliterator(), false)
                .map(this::convertToServiceDto)
                .collect(Collectors.toList());

        result.setStatus(0); // Success
        result.setMessage("Sukses");
        result.setData(services);

        return result;
    }

    private ServiceDto convertToServiceDto(Service service) {
        return new ServiceDto(
                service.getServiceCode(),
                service.getServiceName(),
                service.getServiceIcon(),
                service.getServiceTariff()
        );
    }
}
