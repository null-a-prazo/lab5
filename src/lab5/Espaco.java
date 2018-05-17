
package lab5;

/**
 *
 * Classe que armazena um objeto
 * @param <T> Recebe um robo, ou um obstaculo
 */

public class Espaco<T>{
    private T conteudo;
/**
 * Um novo espaco eh sempre inicializado vazio
 */
    public Espaco() {
        this.conteudo = null;
    }
    
    /**
     * Para saber se o espaco esta ocupado
     * @return true se o espaco esta livre<br>false se o espaco esta ocupado
     */
    public boolean isLivre(){
        return conteudo == null;
    }
    
    /**
     * Insere um objeto
     * @param objeto Qualquer objeto cabe aqui, contanto que nao esteja ocupado
     * TODO um throw caso o espaco ja esteja ocupado
     */
    public void inserir(T objeto) throws UnsupportedOperationException{
        if(isLivre())
            this.conteudo = objeto;
        else
            throw new UnsupportedOperationException("Dois corpos nao ocupam o mesmo espaco.");
    }
    
    
    /**
     * Remove o objeto de sua posicao, e o retorna
     * Similar ao pop()
     * @return O objeto contido nesse espaco
     */
    public T ejetar(){
        T exp = this.conteudo;
        this.conteudo = null;
        return exp;
    }
    
    public void limpar(){
        this.conteudo = null;
    }
    /**
     * Metodo para criar uma representacao legivel do conteudo desse espaco
     * Caso o espaco esteja vazio, retorna um espaco em branco " "
     * @return Uma string representando o conteudo do espaco
     */
    @Override
    public String toString(){
        if(isLivre())
            return " ";
        return this.conteudo.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Espaco<?> other = (Espaco<?>) obj;
        
        if(this.isLivre()!=other.isLivre())
            return false;
        
        if(!this.isLivre() && !other.isLivre())
            if(this.conteudo.getClass()!=other.conteudo.getClass()) {
                return false;
        }
        
        return true;
    }
    
    
}
