package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity(versionField = "", table = "player")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "languages", "achieveds", "boardgames", "playeds", "sex", "levelId", "country" })
public class Player {
}
