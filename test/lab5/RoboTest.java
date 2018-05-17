
package lab5;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoboTest {
	private Sala sala;
        private Robo robo;
	private final int NUM_LINHAS = 10;
	private final int NUM_COLUNAS = 10;
        private final int ENERGIA_INICIAL = 10;

	@Before
	public void criaObjetos() throws Exception {
		sala = new Sala(NUM_LINHAS, NUM_COLUNAS);
                robo = new Robo(ENERGIA_INICIAL, sala);
	}

	@Test
	public void testaCriaRobo() {
		try {
			new Robo(-1, sala);
			Assert.fail("Esperava excecao, pois a quantidade de energia esta negativa.");
		} catch (Exception e) {
			Assert.assertEquals(
					"Mensagem errada",
					"Parametro incorreto para a criacao do robo. A energia do robo deve ser representada por um valor positivo maior que zero",
					e.getMessage());
		}

		try {
			new Robo(0, sala);
			Assert.fail("Esperava excecao, pois a quantidade energia esta zerada.");
		} catch (Exception e) {
			Assert.assertEquals(
					"Mensagem errada",
					"Parametro incorreto para a criacao do robo. A energia do robo deve ser representada por um valor positivo maior que zero",
					e.getMessage());
		}

		try {
                        Sala sala2 = null;
			new Robo(1, sala2);
			Assert.fail("Esperava excecao, pois a instancia de sala nao e valida.");
		} catch (Exception e) {
			Assert.assertEquals(
					"Mensagem errada",
					"Parametro incorreto para a criacao do robo. A sala do robo deve ser representada por uma instancia de Sala() valida",
					e.getMessage());
		}

		try {
                        Sala sala2 = null;
			new Robo(-1, sala2);
			Assert.fail("Esperava excecao, pois a quantidade de energia esta negativa, alem disso, a instancia da sala nao eh valida.");
		} catch (Exception e) {
			Assert.assertEquals(
					"Mensagem errada",
					"Parametro incorreto para a criacao do robo. A energia do robo deve ser representada por um valor positivo maior que zero",
					e.getMessage());
		}
                
		try {
                        int linhas = 1;
                        int colunas = 1;
                        Sala sala2 = new Sala(linhas,colunas);//sala de 1x1
                        sala2.inserirObstaculo(0, 0);//obstaculo
			new Robo(ENERGIA_INICIAL, sala2);
			Assert.fail("Esperava excecao, pois a sala esta cheia.");
		} catch (Exception e) {
			Assert.assertEquals(
					"Mensagem errada",
					"Impossivel criar uma nova instancia de Robo em uma sala cheia",
					e.getMessage());
		}

	}
	
	@Test
	public void testaPosicionamento() {
		Assert.assertFalse(sala.isPosicaoLivre(0, 0));
		Assert.assertFalse(sala.isVazia());
		
		Assert.assertTrue(robo.getPosicaoX() == 0);
		Assert.assertTrue(robo.getPosicaoY() == 0);
                
                Sala sala2 = new Sala(4,4);
                sala2.inserirObstaculo(0, 0);
                Robo robo2 = new Robo(10, sala2);
                Assert.assertTrue(robo2.getPosicaoX() == 1);
                Assert.assertTrue(robo2.getPosicaoY() == 0);
	}

	@Test
	public void testaMovimentacao() {
		Assert.assertFalse(robo.moveCima());
                Assert.assertTrue(robo.getEnergia() == ENERGIA_INICIAL);
		Assert.assertTrue(robo.getPosicaoX() == 0);
		Assert.assertTrue(robo.getPosicaoY() == 0);
                Assert.assertTrue(robo.getPassos()==0);
                
                Assert.assertFalse(robo.moveTras());
                Assert.assertTrue(robo.getEnergia() == ENERGIA_INICIAL);
		Assert.assertTrue(robo.getPosicaoX() == 0);
		Assert.assertTrue(robo.getPosicaoY() == 0);
                Assert.assertTrue(robo.getPassos()==0);
                
                Assert.assertTrue(robo.moveBaixo());
                Assert.assertTrue(robo.getEnergia() == ENERGIA_INICIAL-1);
		Assert.assertTrue(robo.getPosicaoX() == 0);
		Assert.assertTrue(robo.getPosicaoY() == 1);
                Assert.assertTrue(robo.getPassos()==1);
                
                Assert.assertTrue(robo.moveFrente());
                Assert.assertTrue(robo.getEnergia() == ENERGIA_INICIAL-2);
		Assert.assertTrue(robo.getPosicaoX() == 1);
		Assert.assertTrue(robo.getPosicaoY() == 1);
                Assert.assertTrue(robo.getPassos()==2);
                
                Assert.assertTrue(robo.moveCima());
                Assert.assertTrue(robo.getEnergia() == ENERGIA_INICIAL-3);
		Assert.assertTrue(robo.getPosicaoX() == 1);
		Assert.assertTrue(robo.getPosicaoY() == 0);
                Assert.assertTrue(robo.getPassos()==3);
                
                Assert.assertTrue(robo.moveTras());
                Assert.assertTrue(robo.getEnergia() == ENERGIA_INICIAL-4);
		Assert.assertTrue(robo.getPosicaoX() == 0);
		Assert.assertTrue(robo.getPosicaoY() == 0);
                Assert.assertTrue(robo.getPassos()==4);
	}

	@Test
	public void testaMovimentacaoComObstaculos() {
                Sala sala2 = new Sala(NUM_LINHAS, NUM_COLUNAS);
                Robo robo2 = new Robo(ENERGIA_INICIAL, sala2);
                sala2.inserirObstaculo(1, 0);
		Assert.assertFalse(robo2.moveFrente());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL);
		Assert.assertTrue(robo2.getPosicaoX() == 0);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==0);
                
                sala2.inserirObstaculo(0, 1);
                Assert.assertFalse(robo2.moveBaixo());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL);
		Assert.assertTrue(robo2.getPosicaoX() == 0);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==0);
	}

	@Test
	public void testaMovimentacao2() {
                Sala sala2 = new Sala(3, 3);
                Robo robo2 = new Robo(ENERGIA_INICIAL, sala2);
                
                Assert.assertTrue(robo2.moveFrente());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-1);
		Assert.assertTrue(robo2.getPosicaoX() == 1);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==1);
                
                Assert.assertTrue(robo2.moveFrente());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-2);
		Assert.assertTrue(robo2.getPosicaoX() == 2);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==2);
                
                //Nao pode mover para frente, atingiu o limite (2,0)
                Assert.assertFalse(robo2.moveFrente());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-2);
		Assert.assertTrue(robo2.getPosicaoX() == 2);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==2);
                
                Assert.assertTrue(robo2.moveBaixo());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-3);
		Assert.assertTrue(robo2.getPosicaoX() == 2);
		Assert.assertTrue(robo2.getPosicaoY() == 1);
                Assert.assertTrue(robo2.getPassos()==3);
                
                Assert.assertTrue(robo2.moveBaixo());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-4);
		Assert.assertTrue(robo2.getPosicaoX() == 2);
		Assert.assertTrue(robo2.getPosicaoY() == 2);
                Assert.assertTrue(robo2.getPassos()==4);
                
                //Nao pode mover para baixo, atingiu o limite (2,2)
                Assert.assertFalse(robo2.moveBaixo());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-4);
		Assert.assertTrue(robo2.getPosicaoX() == 2);
		Assert.assertTrue(robo2.getPosicaoY() == 2);
                Assert.assertTrue(robo2.getPassos()==4);
                
                Assert.assertTrue(robo2.moveTras());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-5);
		Assert.assertTrue(robo2.getPosicaoX() == 1);
		Assert.assertTrue(robo2.getPosicaoY() == 2);
                Assert.assertTrue(robo2.getPassos()==5);
                
                Assert.assertTrue(robo2.moveTras());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-6);
		Assert.assertTrue(robo2.getPosicaoX() == 0);
		Assert.assertTrue(robo2.getPosicaoY() == 2);
                Assert.assertTrue(robo2.getPassos()==6);
                
                //Nao pode mover para tras, atingiu o limite (0,2)
                Assert.assertFalse(robo2.moveTras());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-6);
		Assert.assertTrue(robo2.getPosicaoX() == 0);
		Assert.assertTrue(robo2.getPosicaoY() == 2);
                Assert.assertTrue(robo2.getPassos()==6);
                
                Assert.assertTrue(robo2.moveCima());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-7);
		Assert.assertTrue(robo2.getPosicaoX() == 0);
		Assert.assertTrue(robo2.getPosicaoY() == 1);
                Assert.assertTrue(robo2.getPassos()==7);
                
                Assert.assertTrue(robo2.moveCima());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-8);
		Assert.assertTrue(robo2.getPosicaoX() == 0);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==8);
                
                //Continuar movendo ate acabar a energia
                Assert.assertTrue(robo2.moveFrente());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-9);
		Assert.assertTrue(robo2.getPosicaoX() == 1);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==9);
                
                //Continuar movendo ate acabar a energia
                Assert.assertTrue(robo2.moveFrente());
                Assert.assertTrue(robo2.getEnergia() == ENERGIA_INICIAL-10);
		Assert.assertTrue(robo2.getPosicaoX() == 2);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==10);
                
                //Nao pode mover, acabou a energia
                Assert.assertFalse(robo2.moveBaixo());
                Assert.assertTrue(robo2.getEnergia() == 0);
		Assert.assertTrue(robo2.getPosicaoX() == 2);
		Assert.assertTrue(robo2.getPosicaoY() == 0);
                Assert.assertTrue(robo2.getPassos()==10);
	}

	@Test
	public void testaEquals() {
		Sala outraSala = null;
                Robo outroRobo = null;
		try {
			outraSala = new Sala(NUM_LINHAS, NUM_COLUNAS);
                        outroRobo = new Robo(ENERGIA_INICIAL, outraSala);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertTrue(robo.equals(outroRobo));
		outraSala.inserirObstaculo(5, 5);
		Assert.assertFalse(robo.equals(outroRobo));
		sala.inserirObstaculo(5, 5);
		Assert.assertTrue(robo.equals(outroRobo));	
		
		sala.inserirObstaculo(0, 9);
		Assert.assertFalse(robo.equals(outroRobo));
		outraSala.inserirObstaculo(0, 9);
		Assert.assertTrue(robo.equals(outroRobo));
		
		sala.inserirObstaculo(9, 9);
		Assert.assertFalse(robo.equals(outroRobo));
		outraSala.inserirObstaculo(9, 9);
		Assert.assertTrue(robo.equals(outroRobo));
	}

}
