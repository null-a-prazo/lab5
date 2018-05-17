package lab5;

/**
 *
 * @author usuario
 */
public class Sala {
    private final Espaco[][] espacos;
    
    private final int numPosVerticais;
    private final int numPosHorizontais;
    
    /**
     * Variaveis estaticas
     */
    public static boolean OCUPADO = true;
    public static boolean LIVRE = false;

    public Sala(int numPosHorizontais, int numPosVerticais) throws IllegalArgumentException{
        /*popular com espacos vazios automagicamente
        */
        if(numPosVerticais<=0 || numPosHorizontais<=0)
            throw new IllegalArgumentException("Parametros incorretos para a criacao da sala. As dimensoes da sala devem ser representadas por valores positivos maiores que zero");
        else{
            this.espacos = new Espaco[numPosHorizontais][numPosVerticais];
            this.numPosVerticais = numPosVerticais;
            this.numPosHorizontais = numPosHorizontais;
            preencher();
        }
    }

    private void preencher() {
        for(int j=0;j<this.numPosVerticais;j++){
            for(int i=0;i<this.numPosHorizontais;i++){
                this.espacos[i][j] = new Espaco();
            }
        }
    }
    public int getNumPosicoesVerticais() {
        return numPosVerticais;
    }

    public int getNumPosicoesHorizontais() {
        return numPosHorizontais;
    }
    
    public boolean mover(int origemX, int origemY, int destinoX, int destinoY){
        if(!posicaoValida(origemX, origemY) || !posicaoValida(destinoX, destinoY) || !isPosicaoLivre(destinoX, destinoY))
            return false;
        this.espacos[destinoX][destinoY].inserir(this.espacos[origemX][origemY].ejetar());
        return true;
    }

    public boolean inserirRobo(int x, int y, Robo mech){
        try{
            this.espacos[x][y].inserir(mech);
            return true;
        }catch (ArrayIndexOutOfBoundsException|UnsupportedOperationException e){
            return false;
        }
    }
    
    public boolean inserirObstaculo(int x, int y){
        try{
            this.espacos[x][y] = new Espaco();
            this.espacos[x][y].inserir(new Obstaculo());
            return true;
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    boolean isVazia() {
        for(int j=0;j<this.numPosVerticais;j++){
            for(int i=0;i<this.numPosHorizontais;i++){
                if(!this.espacos[i][j].isLivre())
                    return false;
            }
        }
        return true;
    }

    boolean posicaoValida(int x, int y) {
        if(x<espacos.length && y<espacos[0].length)
            if(x>=0 && y>=0)
                return true;
        return false;
    }

    public boolean isPosicaoLivre(int x, int y) throws IllegalArgumentException{
        if(x<0 || y<0 || x>=numPosHorizontais || y>=numPosVerticais)
            throw new IllegalArgumentException("Posicao inexistente.");
        else{
            return this.espacos[x][y].isLivre();
        }
    }

    boolean setPosicao(int x, int y, boolean OCUPADO) {
        if(!posicaoValida(x, y))
            return false;
        if(OCUPADO == Sala.LIVRE)
            espacos[x][y].limpar();
        else
            inserirObstaculo(x, y);
        return true;
    }

    @Override
    public String toString() {
        String text = "";
        
        for(int j=0;j<this.numPosVerticais;j++){
            for(int i=0;i<this.numPosHorizontais;i++){
                text+= this.espacos[i][j].toString();
                if(i!=this.numPosHorizontais-1)
                    text+= "|";
            }
            text+="\n";
        }
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        /*if (this == obj) {
            return true;
        }*/
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sala other = (Sala) obj;
        if (this.numPosVerticais != other.numPosVerticais) {
            return false;
        }
        if (this.numPosHorizontais != other.numPosHorizontais) {
            return false;
        }
        for(int j=0;j<this.numPosVerticais;j++)
            for(int i=0;i<this.numPosHorizontais;i++)
                if(!this.espacos[i][j].equals(other.espacos[i][j]))
                    return false;
        return true;
    }
}
