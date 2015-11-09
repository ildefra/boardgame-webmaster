package org.m4.bgw.domain.translate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalLinkRepository extends JpaSpecificationExecutor<ExternalLink>, JpaRepository<ExternalLink, Integer> {
}
