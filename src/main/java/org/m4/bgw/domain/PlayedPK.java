package org.m4.bgw.domain;

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

@Embeddable
@Configurable
public final class PlayedPK implements Serializable {

    private static final long serialVersionUID = 1L;

	@Column(name = "username", nullable = false, length = 20)
    private String username;

	@Column(name = "game_table_id", nullable = false)
    private Integer gameTableId;

	public PlayedPK(String username, Integer gameTableId) {
        super();
        this.username = username;
        this.gameTableId = gameTableId;
    }

	public PlayedPK() {
        super();
    }

	
	public String getUsername() {
        return username;
    }

	public Integer getGameTableId() {
        return gameTableId;
    }

	
	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static PlayedPK fromJsonToPlayedPK(String json) {
        return new JSONDeserializer<PlayedPK>()
        .use(null, PlayedPK.class).deserialize(json);
    }

	public static String toJsonArray(Collection<PlayedPK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<PlayedPK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<PlayedPK> fromJsonArrayToPlayedPKs(String json) {
        return new JSONDeserializer<List<PlayedPK>>()
        .use("values", PlayedPK.class).deserialize(json);
    }

	
	@Override
	public boolean equals(Object obj) {
        if (!(obj instanceof PlayedPK)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PlayedPK rhs = (PlayedPK) obj;
        return new EqualsBuilder()
                .append(gameTableId, rhs.gameTableId)
                .append(username, rhs.username).isEquals();
    }

	@Override
	public int hashCode() {
        return new HashCodeBuilder()
                .append(gameTableId).append(username).toHashCode();
    }
}
