/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.education.hoe.herolevel.dao;

import java.util.List;

public interface HeroLevelDAO {
    HeroLevel create(HeroLevel d);
    List<HeroLevel> getAll();
    HeroLevel getById(long id);
    HeroLevel update(HeroLevel d);
    void delete(long id);   
}
