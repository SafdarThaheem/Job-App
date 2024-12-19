package com.ms.companyMS.company;


import com.ms.companyMS.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getCompanies() {
        Optional<List<Company>> companyList = companyService.getAllCompanies();
        if (companyList.get().isEmpty()) {
            ApiResponse failApiResponse = new ApiResponse(
                    HttpStatus.NOT_FOUND.name(),
                    "Companies Not Found",
                    companyList.get()
            );
            return new ResponseEntity<>(failApiResponse, HttpStatus.NOT_FOUND);
        }
        ApiResponse successApiResponse = new ApiResponse(
                HttpStatus.OK.name(),
                "Companies Found",
                companyList.get()
        );
        return new ResponseEntity<>(successApiResponse, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.addCompany(company);
        return new ResponseEntity<>("Company created", HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable long id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
        return new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable long id) {
        Company company = companyService.getCompany(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable long id) {
        boolean deleted = companyService.deleteCompany(id);
        if (deleted) {
            return new ResponseEntity<>("Company Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
