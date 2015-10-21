package org.m4.bgw.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayedRepository extends JpaRepository<Played, PlayedPK>, JpaSpecificationExecutor<Played> {
}