package org.m4.bgw;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "language")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "players", "achievementTranslations", "externalLinks", "gameTranslations", "levelTranslations", "tagTranslations" })
public class Language {
}
