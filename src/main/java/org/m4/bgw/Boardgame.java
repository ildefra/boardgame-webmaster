package org.m4.bgw;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "boardgame")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "gameDesigners", "gameDesigners1", "gameTags2", "achieveds", "avgGameLengths", "externalLinks", "gameTables", "gameTranslations", "publisher", "developedBy" })
public class Boardgame {
}
