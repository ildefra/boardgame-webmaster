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

@Embeddable
@Configurable
@RooIdentifier(dbManaged = true)
@RooEquals
public final class AchievedPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "username", nullable = false, length = 20)
    private String username;

	@Column(name = "boardgame_id", nullable = false)
    private Integer boardgameId;

	@Column(name = "achievement_id", nullable = false)
    private Short achievementId;

	public AchievedPK(String username, Integer boardgameId, Short achievementId) {
        super();
        this.username = username;
        this.boardgameId = boardgameId;
        this.achievementId = achievementId;
    }

	private AchievedPK() {
        super();
    }

	public String getUsername() {
        return username;
    }

	public Integer getBoardgameId() {
        return boardgameId;
    }

	public Short getAchievementId() {
        return achievementId;
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static AchievedPK fromJsonToAchievedPK(String json) {
        return new JSONDeserializer<AchievedPK>()
        .use(null, AchievedPK.class).deserialize(json);
    }

	public static String toJsonArray(Collection<AchievedPK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<AchievedPK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<AchievedPK> fromJsonArrayToAchievedPKs(String json) {
        return new JSONDeserializer<List<AchievedPK>>()
        .use("values", AchievedPK.class).deserialize(json);
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof AchievedPK)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AchievedPK rhs = (AchievedPK) obj;
        return new EqualsBuilder().append(achievementId, rhs.achievementId).append(boardgameId, rhs.boardgameId).append(username, rhs.username).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(achievementId).append(boardgameId).append(username).toHashCode();
    }
}
