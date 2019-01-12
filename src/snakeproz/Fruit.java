/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeproz;
import java.util.Random;
/**
 *
 * @author Kajkacz
 */
    public final class Fruit{
        int x;
        int y;
        private static volatile Fruit obj = null;

        public static Fruit getFruit(){
            if(obj ==null)
                obj = new Fruit();
            return obj;
        }
        public static Fruit newFruit(){
            obj = new Fruit();
            return obj;
        }
        
        private Fruit(){
            Random rand = new Random(System.currentTimeMillis());
            x = rand.nextInt(AppConstants.boardSize);
            y = rand.nextInt(AppConstants.boardSize);
        }
        
    }