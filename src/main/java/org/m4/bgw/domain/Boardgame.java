package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity(versionField = "", table = "boardgame")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "gameDesigners", "gameDesigners1", "gameTags2", "achieveds", "avgGameLengths", "externalLinks", "gameTables", "gameTranslations", "publisher", "developedBy" })
public class Boardgame {
}
