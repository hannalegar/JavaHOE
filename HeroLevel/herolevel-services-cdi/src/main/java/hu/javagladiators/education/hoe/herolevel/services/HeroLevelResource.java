/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.education.hoe.herolevel.services;

import java.util.List;
import hu.javagladiators.education.hoe.herolevel.dao.HeroLevel;
import hu.javagladiators.education.hoe.herolevel.dao.HeroLevelException;

public interface HeroLevelResource {
    HeroLevel create(HeroLevel d) throws HeroLevelException;
    List<HeroLevel> getAll() throws HeroLevelException;
    HeroLevel getById(long id) throws HeroLevelException;
    HeroLevel update(HeroLevel d) throws HeroLevelException;
    void delete(long id) throws HeroLevelException;
}
