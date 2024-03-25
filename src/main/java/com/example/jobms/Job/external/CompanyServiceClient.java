package com.example.jobms.Job.external;

import com.example.jobms.Job.DTO.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompanyServiceClient {


    private final RestTemplate restTemplate;

    @Autowired
    public CompanyServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CompanyDTO getCompanyById(int companyId) {
        String url = "http://localhost:8081/companies/" + companyId;
        ResponseEntity<CompanyDTO> response = restTemplate.getForEntity(url, CompanyDTO.class);
        // Error handling and response processing
        return response.getBody();
    }
}
