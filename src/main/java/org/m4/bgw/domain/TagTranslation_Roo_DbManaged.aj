// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.m4.bgw.Language;
import org.m4.bgw.domain.GameTag;
import org.m4.bgw.domain.TagTranslation;

privileged aspect TagTranslation_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "tag_name", referencedColumnName = "name", nullable = false, insertable = false, updatable = false)
    private GameTag TagTranslation.tagName;
    
    @ManyToOne
    @JoinColumn(name = "language_code", referencedColumnName = "language_code", nullable = false, insertable = false, updatable = false)
    private Language TagTranslation.languageCode;
    
    @Column(name = "name", length = 20)
    @NotNull
    private String TagTranslation.name;
    
    public GameTag TagTranslation.getTagName() {
        return tagName;
    }
    
    public void TagTranslation.setTagName(GameTag tagName) {
        this.tagName = tagName;
    }
    
    public Language TagTranslation.getLanguageCode() {
        return languageCode;
    }
    
    public void TagTranslation.setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }
    
    public String TagTranslation.getName() {
        return name;
    }
    
    public void TagTranslation.setName(String name) {
        this.name = name;
    }
    
}
