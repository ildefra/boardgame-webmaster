package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.javabean.annotations.RooEquals;

@RooJavaBean
@RooJpaActiveRecord(identifierType = AchievedPK.class, versionField = "", table = "achieved")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "achievementId", "boardgameId", "username" })
@RooEquals
public class Achieved {
}
