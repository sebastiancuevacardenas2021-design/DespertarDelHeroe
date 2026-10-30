public class Caballero 
{   // Atributos del caballero
    static final int VIDA_MAXIMA = 100;
    static final int NUMERO_POCIONES = 3;
    static final int CURACION_POCION = 25;
    static final int DANO_MINIMO = 10;
    static final int DANO_MAXIMO = 20;

    // Atributos del jugador
        String nombre;
        int vida;
        int pocionesRestantes; 
        Armas arma;
    
    // Constructor
        public Caballero(String nombre, Armas arma) 
    {
        this.nombre = nombre;
        this.arma = arma;
        this.vida = VIDA_MAXIMA;
        this.pocionesRestantes = NUMERO_POCIONES; 
    }
    
    // Ataque del Caballero 
        public int atacar() 
    {
    // Daño aleatorio entre el daño minimo y el maximo 
        int DanoBase = (int) (Math.random() * (DANO_MAXIMO - DANO_MINIMO +1)) + DANO_MINIMO;
        
    // Daño del caballero sumado mas el arma 
        return DanoBase + arma.getDaño();
    }
    // Método para que el caballero reciba daño 
         public void recibirDano(int cantidad) 
    {
        this.vida -= cantidad; // Restamos el daño a la vida actual
        if (this.vida < 0) {
            this.vida = 0; // La vida no puede bajar de 0
        }
    }
    //Metodo para usar pocion
        public void usarPocion() 
        {
        if(this.pocionesRestantes > 0) {
           this.pocionesRestantes--;
           this.vida += CURACION_POCION;
    //La vida no puede psar de lo maximo que se permite
        if (this.vida > VIDA_MAXIMA) {
            this.vida = VIDA_MAXIMA;
        }
        System.out.println(this.nombre + "Usaste una pocion, tu vida actual es:" + this.vida);
        
        }
        }
}






