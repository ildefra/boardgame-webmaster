package org.m4.bgw.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RooJpaRepository(domainType = ExternalLink.class)
public interface ExternalLinkRepository extends JpaSpecificationExecutor<ExternalLink>, JpaRepository<ExternalLink, Integer> {
}
