public class Enemigos 
{
    String nombre;
    int VIDA_ACTUAL;
    int VIDA_MAXIMA;
    int DANO_MINIMO;
    int DANO_MAXIMO;
   
    //Constructor del enemigo 
   public Enemigos(String nombre, int VIDA_MAXIMA, int DANO_MINIMO, int DANO_MAXIMO){
    this.nombre = nombre;
    this.VIDA_MAXIMA = VIDA_MAXIMA;
    this.VIDA_ACTUAL = VIDA_MAXIMA;
    this.DANO_MINIMO = DANO_MINIMO;
    this.DANO_MAXIMO = DANO_MAXIMO;
   }

   //Meteodo para atacar
   public int atacar() {
    return (int) (Math.random() * (DANO_MAXIMO - DANO_MINIMO + 1)) + DANO_MINIMO;
   }

   //Metodo para atacar al caballero 
   public void recibirDano(int cantidad) {
    this.VIDA_ACTUAL -=cantidad;
    if(this.VIDA_ACTUAL < 0) {
       this.VIDA_ACTUAL = 0; 
    }
  } 

  //Metodo para ver si el caballero esta vivo
  public boolean estaVivo() {
    return this.VIDA_ACTUAL > 0;
  }

}
