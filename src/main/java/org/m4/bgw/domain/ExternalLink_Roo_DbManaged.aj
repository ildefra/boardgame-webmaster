// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.m4.bgw.Boardgame;
import org.m4.bgw.Language;
import org.m4.bgw.domain.ExternalLink;

privileged aspect ExternalLink_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "boardgame_id", referencedColumnName = "boardgame_id", nullable = false)
    private Boardgame ExternalLink.boardgameId;
    
    @ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "language_code", nullable = false)
    private Language ExternalLink.languageCode;
    
    @Column(name = "name", length = 100)
    @NotNull
    private String ExternalLink.name;
    
    @Column(name = "is_video")
    @NotNull
    private boolean ExternalLink.isVideo;
    
    public Boardgame ExternalLink.getBoardgameId() {
        return boardgameId;
    }
    
    public void ExternalLink.setBoardgameId(Boardgame boardgameId) {
        this.boardgameId = boardgameId;
    }
    
    public Language ExternalLink.getLanguageCode() {
        return languageCode;
    }
    
    public void ExternalLink.setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }
    
    public String ExternalLink.getName() {
        return name;
    }
    
    public void ExternalLink.setName(String name) {
        this.name = name;
    }
    
    public boolean ExternalLink.isIsVideo() {
        return isVideo;
    }
    
    public void ExternalLink.setIsVideo(boolean isVideo) {
        this.isVideo = isVideo;
    }
    
}
