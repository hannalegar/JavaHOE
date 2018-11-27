/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.education.hoe.herolevel.jpa;

import hu.javagladiators.education.hoe.herolevel.dao.HeroLevel;
import hu.javagladiators.education.hoe.herolevel.dao.HeroLevelDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class HeroLevelIMP implements HeroLevelDAO {
    
    EntityManager em;
    
    public HeroLevelIMP(){
        this.em = Persistence.createEntityManagerFactory("hoe").createEntityManager();
    }
    
    public HeroLevel create(HeroLevel d) {
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        
        return d;
    }

    @Override
    public List<HeroLevel> getAll() {
        return em.createQuery("SELECT d FROM HeroLevel d").getResultList();
    }

    @Override
    public HeroLevel getById(long id) {
        return (HeroLevel) em.createQuery("SELECT d FROM HeroLevel d WHERE d.id="+id).getSingleResult();
    }

    @Override
    public HeroLevel update(HeroLevel d) {
        em.getTransaction().begin();
        em.merge(d);
        em.getTransaction().commit();
        
        return d;
    }

    @Override
    public void delete(long id) {
        HeroLevel d = getById(id);
        em.getTransaction().begin();
        em.remove(d);
        em.getTransaction().commit();
    }
}
