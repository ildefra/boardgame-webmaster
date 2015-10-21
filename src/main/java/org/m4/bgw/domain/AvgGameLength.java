package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity(identifierType = AvgGameLengthPK.class, versionField = "", table = "avg_game_length")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "boardgameId", "timeLimitId" })
public class AvgGameLength {
}
