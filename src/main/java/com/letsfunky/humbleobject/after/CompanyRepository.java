package com.letsfunky.humbleobject.after;

interface CompanyRepository {
    Company findById(long companyId);
    void save(Company company);
}
