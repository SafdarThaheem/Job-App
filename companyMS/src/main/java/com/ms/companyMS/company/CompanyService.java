package com.ms.companyMS.company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Optional<List<Company>> getAllCompanies();
    boolean updateCompany(Long id, Company company);
    void addCompany(Company company);
    Company getCompany(Long id);
    boolean deleteCompany(Long id);
}
