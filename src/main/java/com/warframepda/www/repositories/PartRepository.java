package com.warframepda.www.repositories;

import com.warframepda.www.models.Part;
import org.springframework.data.repository.CrudRepository;

public interface PartRepository extends CrudRepository<Part, Long> {

    Part findByPartnameIgnoringCase(String name);


}
