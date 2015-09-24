// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.m4.bgw.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.m4.bgw.domain.TagTranslationPK;

privileged aspect TagTranslationPK_Roo_Identifier {
    
    declare @type: TagTranslationPK: @Embeddable;
    
    @Column(name = "tag_name", nullable = false, length = 20)
    private String TagTranslationPK.tagName;
    
    @Column(name = "language_code", nullable = false, length = 3)
    private String TagTranslationPK.languageCode;
    
    public TagTranslationPK.new(String tagName, String languageCode) {
        super();
        this.tagName = tagName;
        this.languageCode = languageCode;
    }

    private TagTranslationPK.new() {
        super();
    }

    public String TagTranslationPK.getTagName() {
        return tagName;
    }
    
    public String TagTranslationPK.getLanguageCode() {
        return languageCode;
    }
    
}
