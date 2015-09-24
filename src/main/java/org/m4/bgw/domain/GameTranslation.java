package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.javabean.annotations.RooEquals;

@RooJavaBean
@RooJpaActiveRecord(identifierType = GameTranslationPK.class, versionField = "", table = "game_translation")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "boardgameId", "languageCode" })
@RooEquals
public class GameTranslation {
}
