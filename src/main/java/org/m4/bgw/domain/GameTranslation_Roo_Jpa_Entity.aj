// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.m4.bgw.domain.GameTranslation;
import org.m4.bgw.domain.GameTranslationPK;

privileged aspect GameTranslation_Roo_Jpa_Entity {
    
    declare @type: GameTranslation: @Entity;
    
    declare @type: GameTranslation: @Table(name = "game_translation");
    
    @EmbeddedId
    private GameTranslationPK GameTranslation.id;
    
    public GameTranslationPK GameTranslation.getId() {
        return this.id;
    }
    
    public void GameTranslation.setId(GameTranslationPK id) {
        this.id = id;
    }
    
}
