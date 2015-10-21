package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.entity.RooJpaEntity;

@RooJavaBean
@RooJpaEntity(identifierType = LevelTranslationPK.class, versionField = "", table = "level_translation")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "languageCode", "levelId" })
public class LevelTranslation {
}
