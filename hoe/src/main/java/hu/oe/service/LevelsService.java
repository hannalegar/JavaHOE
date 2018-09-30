/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.oe.service;

import hu.oe.datamodel.Levels;
import java.util.List;

/**
 *
 * @author hannalegar
 */
public class LevelsService {
    public List<Levels> add(Levels pNewLevels, List<Levels> pDataSource) throws CustomException{        
        if(validator(pNewLevels, pDataSource)){
            pDataSource.add(pNewLevels);
            return pDataSource;
        }
        throw new CustomException();
    }
    
    private boolean validator(Levels pNewLevels, List<Levels> pDataSource) {
        for(Levels h: pDataSource)
                if(h.getName().equals(pNewLevels.getName()))
                    return false;
        return true;
    }
}
