// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.m4.bgw.Boardgame;

privileged aspect Boardgame_Roo_Jpa_Entity {
    
    declare @type: Boardgame: @Entity;
    
    declare @type: Boardgame: @Table(name = "boardgame");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "boardgame_id")
    private Integer Boardgame.boardgameId;
    
    public Integer Boardgame.getBoardgameId() {
        return this.boardgameId;
    }
    
    public void Boardgame.setBoardgameId(Integer id) {
        this.boardgameId = id;
    }
    
}
