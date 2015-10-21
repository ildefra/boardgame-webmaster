package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity(identifierType = GameTranslationPK.class, versionField = "", table = "game_translation")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "boardgameId", "languageCode" })
public class GameTranslation {
}
