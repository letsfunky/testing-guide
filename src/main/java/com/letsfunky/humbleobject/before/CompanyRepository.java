package com.letsfunky.humbleobject.before;

interface CompanyRepository {
    Company findById(long companyId);
    void save(Company company);
}
