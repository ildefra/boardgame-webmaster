// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import org.m4.bgw.domain.Publisher;
import org.m4.bgw.domain.PublisherRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect PublisherRepository_Roo_Jpa_Repository {
    
    declare parents: PublisherRepository extends JpaRepository<Publisher, String>;
    
    declare parents: PublisherRepository extends JpaSpecificationExecutor<Publisher>;
    
    declare @type: PublisherRepository: @Repository;
    
}
