package com.vpath.cloudcontacts.repositories;

import com.vpath.cloudcontacts.model.ContactHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactHolderRepository extends JpaRepository<ContactHolder, Integer>{
}
