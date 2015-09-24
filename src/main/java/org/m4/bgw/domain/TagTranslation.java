package org.m4.bgw.domain;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(identifierType = TagTranslationPK.class, versionField = "", table = "tag_translation")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "tagName", "languageCode" })
public class TagTranslation {
}
