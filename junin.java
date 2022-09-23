package robo;
import robocode.*;
import java.awt.Color;

public class Junin extends Robot{
    // -- MAIN ---
    public void run(){
        //cor do robo
		setColors(Color.darkGray,Color.black,Color.red,Color.lightGray,Color.green); 
        //funções locomotivas em loop: 
        while (true) { 
          for (int DRight=0; DRight < 3; DRight++){
                turnRadarRight(90); //giro do radar em graus
                ahead(100); // andar 
                turnGunRight(90); //giro do tiro em graus
                back(100); // ré
           }	
          for (int DLeft=0; DLeft < 3; DLeft--){
            turnRadarLeft(180); 
            back(150);
            turnGunLeft(180); 
            ahead(150);
          }
       }
    }
    //Metodo ativado ao bater na parede
    public void onHitWall(HitWallEvent evento) {
        turnRight(100);
        ahead(200);
    }
    //Metodo ativado ao levar tiro do inimigo
    public void onHitByBullet(HitByBulletEvent evento) {
        turnLeft(100);
        back(50);
    }
    //Metodo ativado quando radar encontrar o inimigo
    public void onScannedRobot(ScannedRobotEvent evento) {
        miraAtira(evento.getBearing()); // Bearing : Localização em graus do inimigo
        tiro(evento.getEnergy(), evento.getBearing()); // Energia do inimigo
    }
    //Metodo ativado ao entrar contato com outro robo;
    public void onHitRobot(HitRobotEvent evento) {
        miraAtira(evento.getBearing());
        superTiro(evento.getEnergy());
    }

   //Metodo tiro de acordo coma distância
    public void tiro(double juninEnergia, double distancia) {
         if (distancia > 100 || juninEnergia < 30) {
            fire(1);
        }else if (distancia > 50) {
            fire(2);
        }else {
            superTiro(juninEnergia);
        }   
    }
    public void superTiro(double energia) {
        double energiaTiro = (energia / 4) + .1;
        fire(energiaTiro);
    }
    //Metodo para achar a localização do inimigo; 
    public void miraAtira(double distancia) {
        double juninLocal = getHeading() + distancia - getGunHeading(); 

        if (!(juninLocal > -180 && juninLocal <= 180)) {
            while (juninLocal <= -180) {
                juninLocal += 360;
            }
            while (juninLocal > 180) {
                juninLocal -= 360;
            }
        }

        turnGunRight(juninLocal);
    }
}
