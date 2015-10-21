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

@Configurable
@Embeddable
public final class AvgGameLengthPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "boardgame_id", nullable = false)
    private Integer boardgameId;

	@Column(name = "time_limit_id", nullable = false)
    private Integer timeLimitId;

	public AvgGameLengthPK(Integer boardgameId, Integer timeLimitId) {
        super();
        this.boardgameId = boardgameId;
        this.timeLimitId = timeLimitId;
    }

	private AvgGameLengthPK() {
        super();
    }

	public Integer getBoardgameId() {
        return boardgameId;
    }

	public Integer getTimeLimitId() {
        return timeLimitId;
    }

	public boolean equals(Object obj) {
        if (!(obj instanceof AvgGameLengthPK)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AvgGameLengthPK rhs = (AvgGameLengthPK) obj;
        return new EqualsBuilder().append(boardgameId, rhs.boardgameId).append(timeLimitId, rhs.timeLimitId).isEquals();
    }

	public int hashCode() {
        return new HashCodeBuilder().append(boardgameId).append(timeLimitId).toHashCode();
    }

	public String toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }

	public String toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }

	public static AvgGameLengthPK fromJsonToAvgGameLengthPK(String json) {
        return new JSONDeserializer<AvgGameLengthPK>()
        .use(null, AvgGameLengthPK.class).deserialize(json);
    }

	public static String toJsonArray(Collection<AvgGameLengthPK> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }

	public static String toJsonArray(Collection<AvgGameLengthPK> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }

	public static Collection<AvgGameLengthPK> fromJsonArrayToAvgGameLengthPKs(String json) {
        return new JSONDeserializer<List<AvgGameLengthPK>>()
        .use("values", AvgGameLengthPK.class).deserialize(json);
    }
}
