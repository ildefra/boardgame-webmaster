// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.m4.bgw.domain.Boardgame;
import org.m4.bgw.domain.GameTag;
import org.m4.bgw.domain.TagTranslation;

privileged aspect GameTag_Roo_DbManaged {
    
    @ManyToMany(mappedBy = "gameTags2")
    private Set<Boardgame> GameTag.boardgames2;
    
    @OneToMany(mappedBy = "tagName")
    private Set<TagTranslation> GameTag.tagTranslations;
    
    public Set<Boardgame> GameTag.getBoardgames2() {
        return boardgames2;
    }
    
    public void GameTag.setBoardgames2(Set<Boardgame> boardgames2) {
        this.boardgames2 = boardgames2;
    }
    
    public Set<TagTranslation> GameTag.getTagTranslations() {
        return tagTranslations;
    }
    
    public void GameTag.setTagTranslations(Set<TagTranslation> tagTranslations) {
        this.tagTranslations = tagTranslations;
    }
    
}
