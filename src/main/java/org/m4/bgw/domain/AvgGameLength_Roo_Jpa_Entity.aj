// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.m4.bgw.domain.AvgGameLength;
import org.m4.bgw.domain.AvgGameLengthPK;

privileged aspect AvgGameLength_Roo_Jpa_Entity {
    
    declare @type: AvgGameLength: @Entity;
    
    declare @type: AvgGameLength: @Table(name = "avg_game_length");
    
    @EmbeddedId
    private AvgGameLengthPK AvgGameLength.id;
    
    public AvgGameLengthPK AvgGameLength.getId() {
        return this.id;
    }
    
    public void AvgGameLength.setId(AvgGameLengthPK id) {
        this.id = id;
    }
    
}
