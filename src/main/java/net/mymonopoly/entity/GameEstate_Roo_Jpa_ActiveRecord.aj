// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.mymonopoly.entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.mymonopoly.entity.GameEstate;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GameEstate_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager GameEstate.entityManager;
    
    public static final EntityManager GameEstate.entityManager() {
        EntityManager em = new GameEstate().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long GameEstate.countGameEstates() {
        return entityManager().createQuery("SELECT COUNT(o) FROM GameEstate o", Long.class).getSingleResult();
    }
    
    public static List<GameEstate> GameEstate.findAllGameEstates() {
        return entityManager().createQuery("SELECT o FROM GameEstate o", GameEstate.class).getResultList();
    }
    
    public static GameEstate GameEstate.findGameEstate(Long id) {
        if (id == null) return null;
        return entityManager().find(GameEstate.class, id);
    }
    
    public static List<GameEstate> GameEstate.findGameEstateEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM GameEstate o", GameEstate.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void GameEstate.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void GameEstate.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            GameEstate attached = GameEstate.findGameEstate(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void GameEstate.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void GameEstate.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public GameEstate GameEstate.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        GameEstate merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
