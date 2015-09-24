package org.m4.bgw;
import org.springframework.roo.addon.dbre.annotations.RooDbManaged;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.roo.addon.javabean.annotations.RooToString;
import org.springframework.roo.addon.jpa.annotations.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "boardgame")
@RooDbManaged(automaticallyDelete = true)
public class Boardgame {
}
