/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.util;

import com.game.dao.imp.GradeDaoImp;
import com.game.dao.imp.IGameDao;

/**
 *
 * @author Qi
 */
public class DaoFectory {
     public static  IGameDao getGradeDao(){
         return new GradeDaoImp();
    }
}
