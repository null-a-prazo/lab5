/* @author otavio */
package lab5;

import java.util.Objects;

public class Robo {
    
    private int energia;
    private Sala sala;
    private int passos;
    private int posicaoY;
    private int posicaoX;

    public Robo(int energia, Sala sala) throws IllegalArgumentException, UnsupportedOperationException{
        if(energia<=0){
            throw new IllegalArgumentException("Parametro incorreto para a criacao do robo. A energia do robo deve ser representada por um valor positivo maior que zero");
        }else{
            if(sala==null){
                throw new IllegalArgumentException("Parametro incorreto para a criacao do robo. A sala do robo deve ser representada por uma instancia de Sala() valida");
            }else{
                this.sala = sala;
                this.posicaoX = 0;
                this.posicaoY = 0;
                this.passos = 0;
                this.energia = energia;
                inserirRobo();
            }
        }
    }
    
    private boolean inserirRobo() throws UnsupportedOperationException{
        
        for(int j=0;j<sala.getNumPosicoesVerticais();j++){
            for(int i=0;i<sala.getNumPosicoesHorizontais();i++){
                if(sala.isPosicaoLivre(i, j)){
                    posicaoX = i;
                    posicaoY = j;
                    sala.inserirRobo(i, j, this);
                    return true;
                }
            }
        }
        throw new UnsupportedOperationException("Impossivel criar uma nova instancia de Robo em uma sala cheia");
    }
    
    public boolean moveCima(){
        if(energia==0)
            return false;
        if(sala.mover(posicaoX, posicaoY, posicaoX, posicaoY-1)){
            this.posicaoY--;
            this.energia--;
            this.passos++;
            return true;
        }
        return false;
    }
    
    public boolean moveBaixo(){
        if(energia==0)
            return false;
        if(sala.mover(posicaoX, posicaoY, posicaoX, posicaoY+1)){
            this.posicaoY++;
            this.energia--;
            this.passos++;
            return true;
        }
        return false;
    }
    public boolean moveTras(){
        if(energia==0)
            return false;
        if(sala.mover(posicaoX, posicaoY, posicaoX-1, posicaoY)){
            this.posicaoX--;
            this.energia--;
            this.passos++;
            return true;
        }
        return false;
    }
    public boolean moveFrente(){
        if(energia==0)
            return false;
        if(sala.mover(posicaoX, posicaoY, posicaoX+1, posicaoY)){
            this.posicaoX++;
            this.energia--;
            this.passos++;
            return true;
        }
        return false;
    }

    public int getEnergia() {
        return energia;
    }

    public int getPassos() {
        return passos;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Robo other = (Robo) obj;
        if (this.posicaoY != other.posicaoY) {
            return false;
        }
        if (this.posicaoX != other.posicaoX) {
            return false;
        }
        if (!this.sala.equals(other.sala)) {
            return false;
        }
        return true;
    }
    
    

}
