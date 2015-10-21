package org.m4.bgw.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Configurable;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;


@Embeddable
@Configurable
public final class AchievementTranslationPK implements Serializable {

private static final long serialVersionUID = 1L;

@Column(name = "achievement_id", nullable = false)
private Short achievementId;

@Column(name = "language_code", nullable = false, length = 3)
private String languageCode;

public AchievementTranslationPK() { }

public AchievementTranslationPK(Short achievementId, String languageCode) {
    super();
    this.achievementId = achievementId;
    this.languageCode = languageCode;
}


public Short getAchievementId() {return achievementId; }

public String getLanguageCode() {return languageCode; }


public String toJson() {
    return new JSONSerializer().exclude("*.class").serialize(this);
}

public String toJson(String[] fields) {
    return new JSONSerializer().include(fields).exclude("*.class").serialize(this);
}

public static AchievementTranslationPK fromJsonToAchievementTranslationPK(String json) {
    return new JSONDeserializer<AchievementTranslationPK>()
            .use(null, AchievementTranslationPK.class).deserialize(json);
}

public static String toJsonArray(Collection<AchievementTranslationPK> collection) {
    return new JSONSerializer().exclude("*.class").serialize(collection);
}

public static String toJsonArray(Collection<AchievementTranslationPK> collection, String[] fields) {
    return new JSONSerializer().include(fields).exclude("*.class").serialize(collection);
}

public static Collection<AchievementTranslationPK>
        fromJsonArrayToAchievementTranslationPKs(String json) {
    return new JSONDeserializer<List<AchievementTranslationPK>>()
            .use("values", AchievementTranslationPK.class).deserialize(json);
}


@Override
public boolean equals(Object obj) {
    if (!(obj instanceof AchievementTranslationPK)) { return false; }
    if (this == obj) { return true; }
    AchievementTranslationPK rhs = (AchievementTranslationPK) obj;
    return new EqualsBuilder()
            .append(achievementId, rhs.achievementId)
            .append(languageCode, rhs.languageCode).isEquals();
}

@Override
public int hashCode() {
    return new HashCodeBuilder()
            .append(achievementId).append(languageCode).toHashCode();
}
}
