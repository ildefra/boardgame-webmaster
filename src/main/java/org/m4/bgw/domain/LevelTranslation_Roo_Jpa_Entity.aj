// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.m4.bgw.domain.LevelTranslation;
import org.m4.bgw.domain.LevelTranslationPK;

privileged aspect LevelTranslation_Roo_Jpa_Entity {
    
    declare @type: LevelTranslation: @Entity;
    
    declare @type: LevelTranslation: @Table(name = "level_translation");
    
    @EmbeddedId
    private LevelTranslationPK LevelTranslation.id;
    
    public LevelTranslationPK LevelTranslation.getId() {
        return this.id;
    }
    
    public void LevelTranslation.setId(LevelTranslationPK id) {
        this.id = id;
    }
    
}
