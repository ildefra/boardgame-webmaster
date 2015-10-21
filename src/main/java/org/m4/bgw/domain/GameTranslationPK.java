package org.m4.bgw.domain;
import org.springframework.roo.addon.jpa.annotations.identifier.RooIdentifier;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.javabean.annotations.RooEquals;

@Configurable
@Embeddable
@RooIdentifier(dbManaged = true)
@RooEquals
public final class GameTranslationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static GameTranslationPK fromJsonToGameTranslationPK(String json) {
        return new JSONDeserializer<GameTranslationPK>()
        .use(null, GameTranslationPK.class).deserialize(json);
    }

	public static String toJsonArray(Collection<GameTranslationPK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<GameTranslationPK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<GameTranslationPK> fromJsonArrayToGameTranslationPKs(String json) {
        return new JSONDeserializer<List<GameTranslationPK>>()
        .use("values", GameTranslationPK.class).deserialize(json);
    }

	@Column(name = "boardgame_id", nullable = false)
    private Integer boardgameId;

	@Column(name = "language_code", nullable = false, length = 3)
    private String languageCode;

	public GameTranslationPK(Integer boardgameId, String languageCode) {
        super();
        this.boardgameId = boardgameId;
        this.languageCode = languageCode;
    }

	private GameTranslationPK() {
        super();
    }

	public Integer getBoardgameId() {
        return boardgameId;
    }

	public String getLanguageCode() {
        return languageCode;
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof GameTranslationPK)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        GameTranslationPK rhs = (GameTranslationPK) obj;
        return new EqualsBuilder().append(boardgameId, rhs.boardgameId).append(languageCode, rhs.languageCode).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(boardgameId).append(languageCode).toHashCode();
    }
}
