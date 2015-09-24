package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.javabean.annotations.RooEquals;

@RooJavaBean
@RooJpaActiveRecord(identifierType = AvgGameLengthPK.class, versionField = "", table = "avg_game_length")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "boardgameId", "timeLimitId" })
@RooEquals
public class AvgGameLength {
}
