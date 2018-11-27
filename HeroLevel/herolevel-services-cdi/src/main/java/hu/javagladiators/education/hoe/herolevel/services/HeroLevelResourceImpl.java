/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.education.hoe.herolevel.services;

import hu.javagladiators.education.hoe.herolevel.dao.HeroLevel;
import hu.javagladiators.education.hoe.herolevel.dao.HeroLevelDAO;
import hu.javagladiators.education.hoe.herolevel.dao.HeroLevelException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@RequestScoped
public class HeroLevelResourceImpl implements HeroLevelResource {

    @Inject
    HeroLevelDAO dao;
    
    public HeroLevelResourceImpl(){
        
    }
    
    @Override
    public HeroLevel create(HeroLevel d) throws HeroLevelException {
        try {
            validate(d);
            dao.create(d);
            return d;
        } catch (HeroLevelException e) {
            throw e;
        } catch (Exception e) {
            throw new HeroLevelException("Error when saving the new instance");
        }
    }

    @Override
    public List<HeroLevel> getAll() throws HeroLevelException {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new HeroLevelException("Query failed.");
        }
    }

    @Override
    public HeroLevel getById(long id) throws HeroLevelException {
        try {
            return dao.getById(id);
        } catch (NoResultException e) {
            throw new HeroLevelException(id + " cannot be found.");
        } catch (Exception e) {
            throw new HeroLevelException("Error with query for " + id);
        }
    }

    @Override
    public HeroLevel update(HeroLevel d) throws HeroLevelException {
        try {
            validate(d);
            
            dao.update(d);

            return d;
            
        } catch (HeroLevelException e) {
            throw e;
        } catch (NoResultException e) {
            throw new HeroLevelException(d.getId() + " cannot be found.");
        } catch (Exception e) {
            throw new HeroLevelException("Error when updating " + d.getId());
        }
    }

    @Override
    public void delete(long id) throws HeroLevelException {
        try {
            dao.delete(id);
        } catch (NoResultException e) {
            throw new HeroLevelException(id + " cannot be found.");
        } catch (Exception e) {
            throw new HeroLevelException(id + " couldn't be deleted.");
        } 
    }
    
    private void validate(HeroLevel dbu) throws HeroLevelException {
        if(dbu.getFromLevel() > dbu.getUntilLevel())
            throw new HeroLevelException("From level was grater than until level. ");
        if(dbu.getFromLevel() <= 0 || dbu.getUntilLevel() <= 0)
            throw new HeroLevelException("Only positive levels allowed.");
        List<HeroLevel> all = dao.getAll();
        for (HeroLevel d : all) {
            if (dbu.getLevelName().equals(d.getLevelName()) && dbu.getId()!=d.getId())
                throw new HeroLevelException("An instance with this name already exists. ");
            if(!notOverlappingRange(dbu, d))
                throw new HeroLevelException("Overlapping levels for the descriptions. ");
        }
    }
    
    private boolean notOverlappingRange(HeroLevel d, HeroLevel other){
        return (d.getUntilLevel() <= other.getFromLevel() && d.getFromLevel() <= other.getFromLevel()) 
                || (d.getUntilLevel() >= other.getUntilLevel() && other.getFromLevel() >= other.getUntilLevel());
    }
}
